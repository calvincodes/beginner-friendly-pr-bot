package com.github.calvincodes.twitter;

import com.github.calvincodes.twitter.client.LocalTwitterClient;
import com.github.calvincodes.twitter.client.ProductionTwitterClient;
import com.github.calvincodes.twitter.client.TwitterClient;

public class TwitterClientFactory {

    public static TwitterClient getTwitterClient() {
        String environment = System.getenv("FOSC_ENVIRONMENT");
        if ("PROD".equalsIgnoreCase(environment)) {
            return new ProductionTwitterClient();
        } else {
            return new LocalTwitterClient();
        }
    }
}
