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