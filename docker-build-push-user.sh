#!/bin/bash

# Variables
DOCKER_USERNAME="abhishekvanaras"
DOCKER_PASSWORD="abhishek9889"
IMAGE_NAME="payment-service-ms"
TAG="2.1"
DOCKERFILE_PATH="."

# Step 1: Maven build with tests skipped
echo "Starting Maven build..."
mvn clean install -DskipTests

if [ $? -ne 0 ]; then
  echo "Maven build failed. Exiting."
  exit 1
fi
echo "Maven build completed successfully."

# Step 2: Log in to Docker Hub
echo "Logging in to Docker Hub..."
echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin

if [ $? -ne 0 ]; then
  echo "Docker login failed. Exiting."
  exit 1
fi
echo "Logged in to Docker Hub successfully."

# Step 3: Build the Docker image
echo "Building Docker image..."
docker build -t $DOCKER_USERNAME/$IMAGE_NAME:$TAG $DOCKERFILE_PATH

if [ $? -ne 0 ]; then
  echo "Docker build failed. Exiting."
  exit 1
fi
echo "Docker image built successfully."

# Step 4: Push the Docker image to Docker Hub
echo "Pushing Docker image to Docker Hub..."
docker push $DOCKER_USERNAME/$IMAGE_NAME:$TAG

if [ $? -ne 0 ]; then
  echo "Docker push failed. Exiting."
  exit 1
fi
echo "Docker image pushed to Docker Hub successfully."


echo "Docker image $DOCKER_USERNAME/$IMAGE_NAME:$TAG built and pushed successfully."
