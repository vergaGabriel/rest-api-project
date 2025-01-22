FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/rest-api.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]