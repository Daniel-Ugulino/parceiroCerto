FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/BFF-0.0.1-SNAPSHOT.jar /app/BFF.jar

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "BFF.jar"]