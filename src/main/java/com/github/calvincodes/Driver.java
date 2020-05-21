package com.github.calvincodes;

import com.github.calvincodes.database.DatabaseActions;
import com.github.calvincodes.database.DatabaseFactory;
import com.github.calvincodes.github.GitHubIssuesCollector;
import com.github.calvincodes.github.models.SearchIssueResponse;
import com.github.calvincodes.twitter.client.TwitterClient;
import twitter4j.TwitterException;

import java.util.List;

import static com.github.calvincodes.SearchableLabels.SEARCHABLE_LABELS;

public class Driver {
    public static void main(String[] args) {

        List<String> labels = SEARCHABLE_LABELS;

        GitHubIssuesCollector gitHubIssuesCollector = new GitHubIssuesCollector();
        List<SearchIssueResponse> searchIssueResponseList = gitHubIssuesCollector.searchIssues(labels);

        DatabaseActions databaseActions = DatabaseFactory.getDatabaseActions();
        databaseActions.connect();

        TwitterClient twitterClient = new TwitterClient();
        final String HASH_TAGS = "#GitHub #OpenSource #Newbie #FirstTimersOnly #GoodFirstIssue";
        searchIssueResponseList.forEach(searchIssueResponse -> searchIssueResponse.getItems().forEach(searchIssue -> {
            String key = "twitter:id:" + searchIssue.getId();
            if (databaseActions.setKey(key, "1", 2592000L)) {
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

        databaseActions.disconnect();

        System.out.println("Driver run completed");
    }
}
