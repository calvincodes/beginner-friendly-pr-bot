package com.github.calvincodes;

import com.github.calvincodes.database.handler.DatabaseHandler;
import com.github.calvincodes.database.DatabaseFactory;
import com.github.calvincodes.github.GitHubIssuesCollector;
import com.github.calvincodes.github.models.SearchIssueResponse;
import com.github.calvincodes.mailjet.MailjetSender;
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
        final MailjetSender emailSender = new MailjetSender();

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
//                    int sleepSecs = random.nextInt(15) + 45;
                    // TODO: Remove me.
                    int sleepSecs = 0;
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

        // TODO: Remove me.
        emailSender.sendEmail("[Twitter-Bot] Test Email!");

        try {
            String emailCommand = "/bin/sh -c " +
                    "echo 'Driver Test passed.' | " +
                    "mail -s '[Twitter-Bot] Test Email!' " +
                    "-aFrom:" + System.getenv("FOSC_MAILJET_SENDER") +
                    " " + System.getenv("FOSC_MAILJET_RECIPIENT");

//            String emailCommand = "mail -s '[Twitter-Bot] Test Email!' -aFrom:" + System.getenv("FOSC_MAILJET_SENDER") + " " + System.getenv("FOSC_MAILJET_RECIPIENT") + " <<< 'This is the message'";

//            String[] emailCommand = {"/bin/sh", "-c", String.format("echo 'This is also test.' | mail -s '[Twitter-Bot] Test Email!' -aFrom:%s %s", System.getenv("FOSC_MAILJET_SENDER"), System.getenv("FOSC_MAILJET_RECIPIENT"))};

            System.out.println("emailCommand = " + emailCommand);
            Process p = Runtime.getRuntime().exec(emailCommand);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
            // TODO: Remove Me.
            System.err.println("[com.github.calvincodes.Driver] YAYYYY! Sent email successfully.");
        } catch (Exception ex) {
            System.err.println("[com.github.calvincodes.Driver] Exception while sending email.");
            ex.printStackTrace();
        }

//        try {
//            ProcessBuilder processBuilder = new ProcessBuilder();
//            String emailCommand =
//                    "echo 'Driver Test passed.' | " +
//                            "mail -s '[Twitter-Bot] Test Email!' " +
//                            "-aFrom:" + System.getenv("FOSC_MAILJET_SENDER") +
//                            " " + System.getenv("FOSC_MAILJET_RECIPIENT");
//            processBuilder.command("mail", "-s", "ls /home/mkyong/");
//            Process process = processBuilder.start();
//            int exitVal = process.waitFor();
//            if (exitVal == 0) {
//                System.out.println("Success!");
//                System.exit(0);
//            }
//        } catch (Exception ex) {
//            System.err.println("[com.github.calvincodes.Driver] ProcBuilder Exception while sending email.");
//            ex.printStackTrace();
//        }

        System.out.println("[" + Instant.now() + "] Tweeted " + numberOfTweets + " issues.");
        System.out.println("Driver run completed");
    }
}
