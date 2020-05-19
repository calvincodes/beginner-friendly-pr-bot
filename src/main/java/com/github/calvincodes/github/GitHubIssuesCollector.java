package com.github.calvincodes.github;

import com.github.calvincodes.github.client.GitHubIssuesClient;
import com.github.calvincodes.github.models.SearchIssueRequest;
import com.github.calvincodes.github.models.SearchIssueResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * GitHub does not allow OR search on labels. Hence we need this collector class to iterate over various labels.
 */
public class GitHubIssuesCollector {

    public List<SearchIssueResponse> searchIssues(List<String> labels) {

        List<SearchIssueResponse> searchIssueResponseList = new ArrayList<>();
        labels.forEach(label -> {
            GitHubIssuesClient client = new GitHubIssuesClient();
            SearchIssueRequest searchIssueRequest = new SearchIssueRequest();
            searchIssueRequest.setState("open"); // TODO: Create enum for type. Label: Good first issue.
            searchIssueRequest.setLabel(label);
            try {
                SearchIssueResponse response = client.search(searchIssueRequest);
                System.out.println("Fetched total " + response.getTotalCount() + " issues for label " + label);
                searchIssueResponseList.add(response);
            } catch (Exception ex) {
                System.err.println("Failed to fetch GitHub issues for label " + label);
                ex.printStackTrace();
            }
        });
        return searchIssueResponseList;
    }
}
