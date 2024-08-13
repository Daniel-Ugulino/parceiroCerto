FROM openjdk:17-jdk-slim

WORKDIR /app

ADD UserService/out/artifacts/UserService_jar/UserService.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]