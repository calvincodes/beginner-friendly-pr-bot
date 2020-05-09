package com.github.calvincodes;

import com.github.calvincodes.client.GitHubIssuesClient;
import com.github.calvincodes.models.SearchIssueRequest;
import com.github.calvincodes.models.SearchIssueResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public class SampleRun {
    public static void main(String[] args) throws IOException, URISyntaxException {

        GitHubIssuesClient client = new GitHubIssuesClient();
        SearchIssueRequest searchIssueRequest = new SearchIssueRequest();
        searchIssueRequest.setState("open"); // TODO: Create enum for type. Label: Good first issue.
        searchIssueRequest.setLabel("good-first-issue");
        SearchIssueResponse response = client.search(searchIssueRequest);
        System.out.println(response);
    }
}
