# Step 1: Build the application using Maven
FROM maven AS build

WORKDIR /app

# Copy source code and pom.xml
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean install -DskipTests

# Step 2: Run the application using OpenJDK
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (default Spring Boot port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
