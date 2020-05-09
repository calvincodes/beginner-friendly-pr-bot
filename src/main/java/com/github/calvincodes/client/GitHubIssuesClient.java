package com.github.calvincodes.client;

import com.github.calvincodes.models.SearchIssueRequest;
import com.github.calvincodes.models.SearchIssueResponse;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class GitHubIssuesClient {

    public SearchIssueResponse search(SearchIssueRequest searchIssueRequest) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder builder = new URIBuilder("https://api.github.com/search/issues");
        StringBuilder queryString = new StringBuilder();
        queryString
                .append("state:").append(searchIssueRequest.getState()).append('+')
                .append("label:").append(searchIssueRequest.getLabel()).append("+")
                .append("type:").append("issue"); // TODO: Create enum for type. Label: Good first issue.
        // Note: Using builder.setParameter was encoding the URL and GitHub was not responding properly for it.
        // Note: Query parameters and custom query component are mutually exclusive (only one can be used).
        builder.setCustomQuery("q=" + queryString.toString() +
                "&sort=" + searchIssueRequest.getSortBy() +
                "&order=" + searchIssueRequest.getOrder());

        HttpGet getRequest = new HttpGet(builder.build());
        getRequest.addHeader("accept", "application/json");

        HttpResponse response = httpClient.execute(getRequest);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException(
                    "Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        String result = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
        response.getEntity().getContent().close();

        Gson gson = new Gson();
        SearchIssueResponse searchIssueResponse = gson.fromJson(result, SearchIssueResponse.class);

        httpClient.close();
        return searchIssueResponse;
    }
}
