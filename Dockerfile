FROM openjdk:17-alpine
#FROM openjdk:17-jdk-slim


#RUN apt-get update
#RUN apt-get install -y curl mc

VOLUME /tmp
ADD build/libs/application-1.0.jar application.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","application.jar"]