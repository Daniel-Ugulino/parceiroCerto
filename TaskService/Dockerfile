FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/TaskService-0.0.1-SNAPSHOT.jar /app/taskService.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "taskService.jar"]