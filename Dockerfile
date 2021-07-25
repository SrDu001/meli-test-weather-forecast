FROM gradle:jdk11-openj9 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:11-jdk-slim
ENV SPRING_PROFILES_ACTIVE=pdn
EXPOSE 80
ARG JAR_FILE=target/*.jar
COPY --from=build /home/gradle/src/build/libs/\*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# docker-compose up -d
# heroku login

# IF APP DOES NOT EXIST
# heroku create
# heroku container:login
# heroku container:push web
# heroku container:release web
# IF APP EXIST
# heroku container:login
# heroku container:push -a <app-name> web
# heroku container:release -a <app-name> web
# heroku -a <app-name> open

