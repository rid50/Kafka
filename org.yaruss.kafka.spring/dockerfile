#FROM eclipse-temurin:21-jre-alpine

FROM eclipse-temurin:21-jre-alpine-3.23

#FROM alpine:latest AS build-stage
#FROM alpine/java:21-jre
#FROM ubuntu:20.04

USER root

WORKDIR /opt/app

# Install openrc
#RUN apk add --no-cache openrc

RUN apk update && \
    apk add --no-cache nano && \
    rm -rf /var/cache/apk/*

#RUN apk update && apk add nano findutils \
#    rm -rf /var/cache/apk/*
	
#RUN mkdir /etc/docker/
#COPY --chmod=755 ./daemon.json /opt/app/daemon.json
#COPY --chmod=755 ./daemon.json /usr/src/app/daemon.json


COPY ./*.jar app.jar

#COPY create-topics.sh /tmp/create-topics.sh
#RUN chmod +x /tmp/create-topics.sh
#CMD /tmp/create-topics.sh
# COPY --chmod=644 /data/create.txt /tmp/.
# RUN ls -la /tmp
# COPY . /tmp
# RUN ls -R /tmp
# RUN chmod +x /usr/local/bin/create-topics.sh
ENTRYPOINT ["java","-jar","app.jar"]
# ENTRYPOINT ["/bin/cat"]
#CMD ["tail", "-f", "/dev/null"]
#CMD ["sleep", "infinity"]
