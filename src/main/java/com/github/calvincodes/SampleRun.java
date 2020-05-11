package com.github.calvincodes;

import com.github.calvincodes.github.client.GitHubIssuesClient;
import com.github.calvincodes.github.models.SearchIssueRequest;
import com.github.calvincodes.github.models.SearchIssueResponse;
import com.github.calvincodes.twitter.client.TwitterClient;
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

        TwitterClient twitterClient = new TwitterClient();
        try {
            String status = "Retry Wrong Windows artifact names https://github.com/goreleaser/goreleaser/issues/1500";
            twitterClient.tweetStatus(status);
        } catch (TwitterException e) {
//            e.printStackTrace();
        }
    }
}
