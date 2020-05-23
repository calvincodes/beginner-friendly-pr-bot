package com.github.calvincodes.twitter.client;

public class LocalTwitterClient implements TwitterClient {

    @Override
    public void tweetStatus(String status) {
        System.out.println("[env=LOCAL] Successfully updated the status to [" + status + "].");
    }
}
