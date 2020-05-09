package com.github.calvincodes;

import com.github.calvincodes.models.SearchIssueResponse;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SampleRun {
    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //We just change HttpPost > HttpDelete
        HttpGet getRequest = new HttpGet("https://api.github.com/search/issues?q=state%3Aopen+label%3A%22good-first-issue%22&type=Issues");
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
    }
}
