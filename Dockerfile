FROM maven:3 as BUILD_IMAGE
RUN mvn clean package

FROM eclipse-temurin:17-jdk-alpine
RUN mvn clean package
VOLUME /tmp
COPY project-connect-rest/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
