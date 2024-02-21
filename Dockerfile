FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

ARG JAR_FILE
ARG CONFIG_FILE_NAME
ENV ENV_CONFIG_FILE_NAME=$CONFIG_FILE_NAME

RUN mkdir ./config

COPY ./config/$CONFIG_FILE_NAME ./config

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.location=./config/${ENV_CONFIG_FILE_NAME}"]