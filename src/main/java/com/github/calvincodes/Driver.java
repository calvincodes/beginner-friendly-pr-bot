package com.github.calvincodes;

import com.github.calvincodes.database.handler.DatabaseHandler;
import com.github.calvincodes.database.DatabaseFactory;
import com.github.calvincodes.email.MailjetPostfixSender;
import com.github.calvincodes.github.GitHubIssuesCollector;
import com.github.calvincodes.github.models.SearchIssueResponse;
import com.github.calvincodes.twitter.client.TwitterClient;
import com.github.calvincodes.twitter.TwitterClientFactory;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.calvincodes.github.SearchableLabels.SEARCHABLE_LABELS;

public class Driver {
    public static void main(String[] args) {

        List<String> labels = SEARCHABLE_LABELS;
        Random random = new Random();

        // Setup Mailjet
        MailjetPostfixSender emailSender = new MailjetPostfixSender();

        // Collect issues from GitHub
        GitHubIssuesCollector gitHubIssuesCollector = new GitHubIssuesCollector();
        List<SearchIssueResponse> searchIssueResponseList = gitHubIssuesCollector.searchIssues(labels);

        // Setup DB connection
        DatabaseHandler databaseHandler = DatabaseFactory.getDatabaseActions();
        databaseHandler.connect();

        // Tweet issues if not present in the DB
        TwitterClient twitterClient = TwitterClientFactory.getTwitterClient();
        final String HASH_TAGS = "#GitHub #OpenSource #Newbie #FirstTimersOnly #GoodFirstIssue";
        AtomicInteger numberOfTweets = new AtomicInteger();
        searchIssueResponseList.forEach(searchIssueResponse -> searchIssueResponse.getItems().forEach(searchIssue -> {
            String key = "twitter:id:" + searchIssue.getId();
            if (databaseHandler.setKeyIfNotExist(key, "1", 2592000L)) {
                String statusPrefix = searchIssue.getTitle();
                String statusSuffix = " " + searchIssue.getHtmlUrl() + " " + HASH_TAGS;
                if (statusPrefix.length() + statusSuffix.length() >= 280) {
                    statusPrefix = statusPrefix.substring(0, 280 - statusSuffix.length() - 1);
                }
                String status = statusPrefix + statusSuffix;
                twitterClient.tweetStatus(status);
                numberOfTweets.set(numberOfTweets.get() + 1);
                try {
                    int sleepSecs = random.nextInt(15) + 45;
                    Thread.sleep(sleepSecs * 1000);
                } catch (InterruptedException e) {
                    System.err.println("Error while sleeping between tweets.");
                    e.printStackTrace();
                }
            }
        }));

        // Reset DB connection
        databaseHandler.disconnect();

        if (numberOfTweets.get() == 0) {
            emailSender.sendEmail("[Twitter-Bot] Tweeted 0 issues!");
        }

        System.out.println("[" + Instant.now() + "] Tweeted " + numberOfTweets + " issues.");
        System.out.println("Driver run completed");
    }
}
