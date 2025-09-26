# Base image
FROM openjdk:8

# Working directory
WORKDIR /app

# Copy source code and libraries
COPY src ./src
COPY lib ./lib

# Create output folder
RUN mkdir -p out

# Generate sources.txt with all Java files
RUN find src -name "*.java" > sources.txt


RUN javac -source 1.8 -target 1.8 -d out \
    -cp "lib/mysql-connector-j-8.3.0.jar:lib/slf4j-api-1.7.36.jar:lib/logback-classic-1.2.11.jar:lib/logback-core-1.2.11.jar" \
    @sources.txt



RUN jar cfe app.jar app.Main -C out .

# Run the app with all required libraries in classpath
CMD ["java", "-cp", "out:lib/*", "app.Main"]
