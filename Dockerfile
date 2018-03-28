FROM openjdk:8-alpine

ENV TZ=Europe/Brussels
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /srv
COPY ./push-sound.jar push-sound.jar
CMD java -jar push-sound.jar
