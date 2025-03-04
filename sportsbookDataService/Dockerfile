# Step 1: Build the application
FROM maven:3.9.8-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and the source code into the container
COPY pom.xml /app/
COPY src /app/src

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Step 2: Run the application
FROM openjdk:21

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/sportsbookDataService-0.0.1-SNAPSHOT.jar /app/sportsbookDataService-0.0.1-SNAPSHOT.jar

# Expose the port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "sportsbookDataService-0.0.1-SNAPSHOT.jar"]