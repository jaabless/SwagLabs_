# Use a base image with Java 17 and Maven
FROM maven:3.9.6-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Install dependencies and build project
RUN mvn clean install -DskipTests

# Command to run tests and generate Allure report
CMD ["mvn", "clean", "test"]