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

# Setup Mailjet as an SMTP relay
# Sending email using PostFix sender as Java based sender example did not work.
# https://cloud.google.com/compute/docs/tutorials/sending-mail/using-mailjet#configuring_mailjet_as_an_smtp_relay

# Clone GitHub Repo
git clone https://github.com/calvincodes/beginner-friendly-pr-bot.git

# Create fat jar
# cd beginner-friendly-pr-bot
# mvn assembly:assembly -DdescriptorId=jar-with-dependencies

# Start redis
# redis-server --daemonize yes --port 6380
## Reference: How to start redis server started with daemoize -yes
## https://stackoverflow.com/questions/49249229/how-to-stop-a-redis-server-that-was-started-with-daemonize-yes

## Setup crontab

# Pass following values to crontab itself
## Twitter4J setup
#FOSC_TWITTER4J_OAUTH_CONSUMER_KEY
#FOSC_TWITTER4J_OAUTH_CONSUMER_SECRET
#FOSC_TWITTER4J_OAUTH_ACCESS_TOKEN
#FOSC_TWITTER4J_OAUTH_ACCESS_TOKEN_SECRET
## Redis setup
#FOSC_REDIS_HOST
#FOSC_REDIS_PORT
#FOSC_ENVIRONMENT
## Mailjet setup
#FOSC_MAILJET_RECIPIENT
#FOSC_MAILJET_SENDER
## Mailjet client setup (if client starts to work).
#FOSC_MAILJET_API_KEY
#FOSC_MAILJET_SECRET_KEY

# Add cron schedule
# 0 */6 * * * java -jar ${absolute_path_to}/beginner-friendly-pr-bot/target/first-open-source-commit-bot-1.0.0-SNAPSHOT-jar-with-dependencies.jar >> log-file-path 2>&1

