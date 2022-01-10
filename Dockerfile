FROM openjdk:8-jdk-alpine

ENV TZ='UTC'

VOLUME /tmp

EXPOSE 8083

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} sms-envio.jar

ENTRYPOINT ["java","-Xmx1G","-jar","/sms-envio.jar"]
