package com.github.calvincodes.twitter.client;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClient {

    private Twitter TWITTER_CLIENT = null;
    private volatile Boolean IS_CLIENT_INITIALIZED = false;

    // TODO: Create separate config file
    public TwitterClient() {
        synchronized (IS_CLIENT_INITIALIZED) {
            if (!IS_CLIENT_INITIALIZED) {
                ConfigurationBuilder configBuilder = new ConfigurationBuilder();
                configBuilder.setDebugEnabled(true)
                        .setOAuthConsumerKey(System.getenv("FOSC_TWITTER4J_OAUTH_CONSUMER_KEY"))
                        .setOAuthConsumerSecret(System.getenv("FOSC_TWITTER4J_OAUTH_CONSUMER_SECRET"))
                        .setOAuthAccessToken(System.getenv("FOSC_TWITTER4J_OAUTH_ACCESS_TOKEN"))
                        .setOAuthAccessTokenSecret(System.getenv("FOSC_TWITTER4J_OAUTH_ACCESS_TOKEN_SECRET"));
                TwitterFactory twitterFactory = new TwitterFactory(configBuilder.build());
                TWITTER_CLIENT = twitterFactory.getInstance();
                IS_CLIENT_INITIALIZED = true;
            }
        }
    }

    public void tweetStatus(String statusStr) throws TwitterException {
        Status status = TWITTER_CLIENT.updateStatus(statusStr);
        System.out.println("Successfully updated the status to [" + status.getText() + "].");
    }
}
