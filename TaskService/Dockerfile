FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/TaskService-0.0.1-SNAPSHOT.jar /app/taskService.jar
COPY src/main/java/com/example/taskservice/Dataloader/Data /app/data

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "taskService.jar"]