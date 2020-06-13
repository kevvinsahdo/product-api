FROM maven:3.6-jdk-11

WORKDIR /app

COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package
