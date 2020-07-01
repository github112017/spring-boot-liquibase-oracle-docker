FROM openjdk:8-jdk-alpine
MAINTAINER Jean Louis

ADD /target/*.jar .
RUN mv *.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx2g", "-jar", "-Dspring.profiles.active=docker", "app.jar"]