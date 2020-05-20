package com.github.calvincodes;

import com.github.calvincodes.github.GitHubIssuesCollector;
import com.github.calvincodes.github.client.GitHubIssuesClient;
import com.github.calvincodes.github.models.SearchIssueRequest;
import com.github.calvincodes.github.models.SearchIssueResponse;
import com.github.calvincodes.twitter.client.TwitterClient;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.protocol.SetArgs;
import twitter4j.TwitterException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver {
    public static void main(String[] args) throws IOException, URISyntaxException {

        List<String> labels = new ArrayList<>(Arrays.asList("newbie", "first-timers-only", "good-first-issue"));

        GitHubIssuesCollector gitHubIssuesCollector = new GitHubIssuesCollector();
        List<SearchIssueResponse> searchIssueResponseList = gitHubIssuesCollector.searchIssues(labels);

        // TODO: Create separate config file
        String redisHost = System.getenv("FOSC_REDIS_HOST");
        int redisPort = Integer.parseInt(System.getenv("FOSC_REDIS_PORT"));
        RedisClient redisClient = new RedisClient(redisHost, redisPort);
        RedisConnection<String, String> redisConnection = redisClient.connect();

        System.out.println("Connected to Redis");

        TwitterClient twitterClient = new TwitterClient();
        final String HASH_TAGS = "#GitHub #OpenSource #Newbie #FirstTimersOnly #GoodFirstIssue";
        searchIssueResponseList.forEach(searchIssueResponse -> searchIssueResponse.getItems().forEach(searchIssue -> {
            String key = "twitter:id:" + searchIssue.getId();
            SetArgs setArgs = SetArgs.Builder.nx().ex(2592000L); // 30 days TTL
            if ("OK".equals(redisConnection.set(key, "1", setArgs))) {
                try {
                    String statusPrefix = searchIssue.getTitle();
                    String statusSuffix = " " + searchIssue.getHtmlUrl() + " " + HASH_TAGS;
                    if (statusPrefix.length() + statusSuffix.length() >= 280) {
                        statusPrefix = statusPrefix.substring(0, 280 - statusSuffix.length() - 1);
                    }
                    String status = statusPrefix + statusSuffix;
                    twitterClient.tweetStatus(status);
                } catch (TwitterException ex) {
                    System.err.println("Error while tweeting");
                    ex.printStackTrace();
                }
            }
        }));

        redisConnection.close();
        redisClient.shutdown();
        System.out.println("Closed Redis connection");

        System.out.println("Driver run completed");
    }
}
