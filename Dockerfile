# Base image
FROM openjdk:8

WORKDIR /app

# Copy source
COPY src/ ./src/

# Copy jar file (ensure it's really a file, not extracted)
COPY lib/mysql-connector-j-8.3.0.jar ./lib/mysql-connector-j-8.3.0.jar


RUN mkdir -p out


# Compile with classpath
RUN find src -name "*.java" -print0 | xargs -0 javac -d out -cp lib/mysql-connector-j-8.3.0.jar

# Create jar
RUN jar cfe app.jar app.Main -C out .

CMD ["sh", "-c", "java -cp app.jar:lib/mysql-connector-j-8.3.0.jar app.Main"]
