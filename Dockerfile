#
# Build stage
#
FROM maven:3.8.3-openjdk-17 as build
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM timbru31/java-node:17-jdk
VOLUME /tmp
COPY /home/app/project-connect-rest/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
