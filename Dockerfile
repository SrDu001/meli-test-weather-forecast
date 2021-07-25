FROM gradle:jdk11-openj9 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:11-jdk-slim
EXPOSE 80
ARG JAR_FILE=target/*.jar
COPY --from=build /home/gradle/src/build/libs/\*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
