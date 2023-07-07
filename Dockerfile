#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY /home/app/project-connect-rest/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
