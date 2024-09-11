FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/RequestService-0.0.1-SNAPSHOT.jar /app/requestService.jar
COPY src/main/java/com/example/requestservice/Dataloader/Data /app/data

EXPOSE 8087

ENTRYPOINT ["java", "-jar", "requestService.jar"]