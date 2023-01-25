FROM openjdk:17-jdk-slim

#RUN apt-get update
#RUN apt-get install -y curl mc

VOLUME /tmp

WORKDIR /application
COPY /build/libs/ /application/build/libs/

#ADD build/libs/application-1.0.jar application.jar

ENV APP_OPTS="\
    -Xms512M -server -Xmx1024M -XX:+UseParallelGC -Djava.awt.headless=true"
#    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

CMD "$JAVA_HOME/bin/java" $APP_OPTS \
-cp ./build/libs/application-1.0.jar \
org.springframework.boot.loader.JarLauncher
