FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/service-registry-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=docker"]
