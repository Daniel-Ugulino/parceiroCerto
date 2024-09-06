FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/ServerRegistry-0.0.1-SNAPSHOT.jar /app/ServerRegistry.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "ServerRegistry.jar"]