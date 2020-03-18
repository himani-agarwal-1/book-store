FROM maven:3.5-jdk-8-alpine AS build
WORKDIR /home/app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn install

FROM openjdk:8-jdk-alpine
WORKDIR /home/app
ARG JAR_FILE=/home/app/target/*.jar
COPY --from=build ${JAR_FILE} /home/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
