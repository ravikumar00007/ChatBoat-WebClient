FROM openjdk:17-alpine
EXPOSE 4041
COPY target/ChatBoat-WebClient-0.0.1-SNAPSHOT.jar chatboat.jar
ENTRYPOINT ["java","-jar","/chatboat.jar"]