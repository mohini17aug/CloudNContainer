# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the build artifacts from the host to the container
COPY build/libs/weborder-0.0.1-SNAPSHOT.jar weborder.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "weborder.jar"]

