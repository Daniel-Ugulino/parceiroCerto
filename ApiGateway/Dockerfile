FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/ApiGateway-0.0.1-SNAPSHOT.jar /app/ApiGateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ApiGateway.jar"]