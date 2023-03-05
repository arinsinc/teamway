FROM openjdk:17-jdk-slim
ENV APP_HOME=/usr/teamway
WORKDIR $APP_HOME
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} teamway-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","teamway-api.jar"]