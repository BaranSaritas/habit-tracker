FROM maven:3-amazoncorretto-21 as build

WORKDIR /usr/src/app

COPY . .

RUN mvn clean package

FROM amazoncorretto:21-alpine3.15-jdk



