FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/UserService-0.0.1-SNAPSHOT.jar /app/UserService.jar
COPY src/main/java/com/example/userservice/Dataloader/Data /app/data

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "UserService.jar"]