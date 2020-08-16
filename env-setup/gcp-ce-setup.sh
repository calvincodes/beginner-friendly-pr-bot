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

# Clone GitHub Repo
git clone https://github.com/calvincodes/first-open-source-commit-bot.git

# Create fat jar
# cd first-open-source-commit-bot
# mvn assembly:assembly -DdescriptorId=jar-with-dependencies

# Start redis
# redis-server --daemonize yes --port 6380
## Reference: How to start redis server started with daemoize -yes
## https://stackoverflow.com/questions/49249229/how-to-stop-a-redis-server-that-was-started-with-daemonize-yes

## Setup crontab

# Pass following values to crontab itself
# FOSC_TWITTER4J_OAUTH_CONSUMER_KEY
# FOSC_TWITTER4J_OAUTH_CONSUMER_SECRET
# FOSC_TWITTER4J_OAUTH_ACCESS_TOKEN
# FOSC_TWITTER4J_OAUTH_ACCESS_TOKEN_SECRET
# FOSC_REDIS_HOST
# FOSC_REDIS_PORT
# FOSC_ENVIRONMENT

# Add cron schedule
# 0 */12 * * * java -jar ${absolute_path_to}/first-open-source-commit-bot/target/first-open-source-commit-bot-1.0.0-SNAPSHOT-jar-with-dependencies.jar
