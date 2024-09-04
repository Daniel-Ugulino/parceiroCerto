FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/ChatService-0.0.1-SNAPSHOT.jar /app/ChatService.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "ChatService.jar"]