# Base image
FROM openjdk:8

# Working directory
WORKDIR /app

# Copy source code and library
COPY src ./src
COPY lib/mysql-connector-j-8.3.0.jar ./lib/mysql-connector-j-8.3.0.jar

# Create output folder
RUN mkdir -p out

# Generate sources.txt with all Java files
RUN find src -name "*.java" > sources.txt

# Compile all Java files with MySQL connector in classpath
RUN javac -source 1.8 -target 1.8 -d out -cp "lib/mysql-connector-j-8.3.0.jar" @sources.txt

# Create executable jar
RUN jar cfe app.jar app.Main -C out .

# Run the app with MySQL connector in classpath
CMD ["java", "-cp", "app.jar:lib/mysql-connector-j-8.3.0.jar", "app.Main"]
