# Use Java 17 runtime
FROM openjdk:17-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the jar into the container
COPY target/parking-lot-reservation-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
