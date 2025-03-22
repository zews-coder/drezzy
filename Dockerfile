# Build stage
FROM maven:3.9.9 AS builder

# Set working directory
WORKDIR /usr/drezzy

# Copy the whole project into Docker image
COPY . .

# Run Maven build (clean, install dependencies, build)
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

# Final stage for user-service
FROM alpine:latest AS user-service

RUN apk add --no-cache openjdk21-jre

WORKDIR /usr/drezzy

COPY --from=builder /usr/drezzy/user-service/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]

# Final stage for shopping-service
FROM alpine:latest AS shopping-service

RUN apk add --no-cache openjdk21-jre

WORKDIR /usr/drezzy

COPY --from=builder /usr/drezzy/shopping-service/target/*.jar /app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app.jar"]