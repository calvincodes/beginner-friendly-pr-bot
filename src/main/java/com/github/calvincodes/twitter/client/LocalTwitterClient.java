package com.github.calvincodes.twitter.client;

import com.github.calvincodes.twitter.TwitterClient;

public class LocalTwitterClient implements TwitterClient {

    @Override
    public void tweetStatus(String status) {
        System.out.println("[env=LOCAL] Successfully updated the status to [" + status + "].");
    }
}
