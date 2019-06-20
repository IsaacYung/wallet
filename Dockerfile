FROM openjdk:8-jdk-alpine

USER root

RUN mkdir /wallet

COPY build/libs/wallet.jar /wallet/wallet.jar
COPY src/main/resources/newrelic /wallet/newrelic

WORKDIR /wallet

ENTRYPOINT ["java", "-javaagent:/wallet/newrelic/newrelic.jar",\
            "-jar", "/wallet/wallet.jar",\
            "-Dnewrelic.environment=$JAVA_ENV"]