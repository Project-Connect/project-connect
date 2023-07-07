#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
RUN apk add maven
RUN apk add --update nodejs npm
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package
VOLUME /tmp
COPY /home/app/project-connect-rest/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
