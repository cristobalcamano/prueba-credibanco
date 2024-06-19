FROM openjdk:17
VOLUME /temp
EXPOSE 8080
ADD ./target/back-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]