#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
RUN apk add maven
RUN apk add --update nodejs npm
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package
VOLUME /tmp
ENTRYPOINT ["java","-jar","/home/app/project-connect-rest/target/project-connect-rest-0.0.0.jar"]
