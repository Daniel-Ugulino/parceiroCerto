FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/FeedbackService-0.0.1-SNAPSHOT.jar /app/FeedbackService.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "FeedbackService.jar"]