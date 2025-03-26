
FROM openjdk:21-jdk-slim
WORKDIR /app

COPY target/fexapp-0.0.1-SNAPSHOT.jar app.jar
COPY .env .env
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
