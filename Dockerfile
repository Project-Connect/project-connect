#
# Build stage
#
FROM maven:3.8.3-openjdk-17 as build
COPY project-connect-rest /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY /home/app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
