FROM java:8

WORKDIR /usr/src/pocket-cep

ADD target/Pocket-CEP-0.8.jar Pocket-CEP.jar

EXPOSE 9999

CMD ["java", "-jar", "Pocket-CEP.jar"]
