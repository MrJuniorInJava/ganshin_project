FROM openjdk:17
ADD /target/Ganshin-0.0.1-SNAPSHOT.jar backend.jar
CMD ["java", "-jar", "backend.jar"]
EXPOSE 8080
