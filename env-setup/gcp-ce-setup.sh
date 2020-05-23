#!/bin/bash

sudo apt update
sudo apt-get update

# Install Git
sudo apt install git

# Install Java JRE and JDK
sudo apt-get install default-jre
sudo apt-get install default-jdk

# Install Maven
sudo apt install maven

# Install Redis
sudo apt-get install redis-server

# Create fat jar
# mvn assembly:assembly -DdescriptorId=jar-with-dependencies

# Setup crontab
# Pass following values to crontab itself
# FOSC_TWITTER4J_OAUTH_CONSUMER_KEY
# FOSC_TWITTER4J_OAUTH_CONSUMER_SECRET
# FOSC_TWITTER4J_OAUTH_ACCESS_TOKEN
# FOSC_TWITTER4J_OAUTH_ACCESS_TOKEN_SECRET
# FOSC_REDIS_HOST
# FOSC_REDIS_PORT
# FOSC_ENVIRONMENT
# Add cron schedule