FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/AuthService-0.0.1-SNAPSHOT.jar /app/AuthService.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "AuthService.jar"]