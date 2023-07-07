#
# Build stage
#
FROM maven:3.8.3-openjdk-17 as build
RUN apt-get install -y curl \
  && curl -sL https://deb.nodesource.com/setup_9.x | bash - \
  && apt-get install -y nodejs \
  && curl -L https://www.npmjs.com/install.sh | sh
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package
#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY /home/app/project-connect-rest/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
