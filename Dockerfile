FROM openjdk:11
EXPOSE 8080
ADD target/finances-api-0.0.1-SNAPSHOT.jar finances-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/finances-api-0.0.1-SNAPSHOT.jar"]