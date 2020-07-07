FROM java:8

WORKDIR /usr/src/pocket-cep

ADD target/Pocket-CEP-0.0.4.jar Pocket-CEP.jar

EXPOSE 8080

CMD ["java", "-jar", "Pocket-CEP.jar"]
