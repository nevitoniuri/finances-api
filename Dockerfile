FROM openjdk:11
EXPOSE 8080

ADD . /root/finances-api

WORKDIR /root/finances-api
RUN pwd
RUN chmod +x mvnw && ./mvnw install -Dmaven.test.skip=true

ENTRYPOINT ["java","-jar","target/finances-api-0.0.1-SNAPSHOT.jar"]