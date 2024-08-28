FROM openjdk:17-jdk-slim

WORKDIR /app

ADD ApiGateway/out/artifacts/ApiGateway_jar/ApiGateway.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]