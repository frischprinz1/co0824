#!/bin/bash

# Setup and run the Java app

backend=toolrentalproject
cd $backend

# Run MySQL in Docker
echo "===================================="
echo "Now setting up the MySQL database..."
echo "===================================="
docker compose up -d

echo "========================================================"
echo "Running Gradle build and executing resulting jar file..."
echo "========================================================"
# Build a jar file and run it
./gradlew clean build && \
java -jar build/libs/toolrentalproject-0.0.1-SNAPSHOT.jar
