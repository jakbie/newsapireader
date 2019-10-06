FROM openjdk:8-jre-alpine
ADD server/target/server-1.0-SNAPSHOT.jar server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "server.jar"]

