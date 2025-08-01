FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/tournament-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=docker"]
