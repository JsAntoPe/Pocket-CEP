FROM openjdk:8-jdk-alpine

COPY target.* target/

ENTRYPOINT["java"]
