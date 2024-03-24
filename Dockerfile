# Use a standard Java image, not Alpine, to ensure broader compatibility
FROM openjdk:17

# Set the working directory in the container to /app
WORKDIR /app

# Copy the built jar file from your target directory into the Docker image
COPY target/dockerize-spring-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Optional: Set environment variables for increased logging
ENV JAVA_OPTS="-Dlogging.level.org.springframework=DEBUG -Dlogging.level.com.yourpackage=DEBUG"

# Run your application with any additional options
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
