package com.github.calvincodes;

import com.github.calvincodes.github.client.GitHubIssuesClient;
import com.github.calvincodes.github.models.SearchIssueRequest;
import com.github.calvincodes.github.models.SearchIssueResponse;
import com.github.calvincodes.twitter.client.TwitterClient;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.protocol.SetArgs;
import twitter4j.TwitterException;

import java.io.IOException;
import java.net.URISyntaxException;

public class SampleRun {
    public static void main(String[] args) throws IOException, URISyntaxException {

        GitHubIssuesClient client = new GitHubIssuesClient();
        SearchIssueRequest searchIssueRequest = new SearchIssueRequest();
        searchIssueRequest.setState("open"); // TODO: Create enum for type. Label: Good first issue.
        searchIssueRequest.setLabel("good-first-issue");
        SearchIssueResponse response = client.search(searchIssueRequest);
//        System.out.println(response);

        // TODO: Create separate config file
        String redisHost = System.getenv("FOSC_REDIS_HOST");
        int redisPort = Integer.parseInt(System.getenv("FOSC_REDIS_PORT"));
        RedisClient redisClient = new RedisClient(redisHost, redisPort);
        RedisConnection<String, String> redisConnection = redisClient.connect();

        System.out.println("Connected to Redis");

        TwitterClient twitterClient = new TwitterClient();

        final int[] i = {0}; // TODO: Remove this
        response.getItems().forEach(searchIssue -> {
            if (i[0] <= 3) { // TODO: Remove this
                String key = "id:" + searchIssue.getId();
                SetArgs setArgs = SetArgs.Builder.nx().ex(60);
                if ("OK".equals(redisConnection.set(key, "1", setArgs))) {
                    try {
                        String status = searchIssue.getTitle() + " " + searchIssue.getHtmlUrl();
                        twitterClient.tweetStatus(status);
                    } catch (TwitterException e) {
                        e.printStackTrace();
                    }
                }
            }
            i[0] = i[0] + 1; // TODO: Remove this
        });

        redisConnection.close();
        redisClient.shutdown();
    }
}
