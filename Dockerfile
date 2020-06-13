FROM maven:3.6-jdk-11 AS build

WORKDIR /app

COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package

FROM openjdk:11

WORKDIR /app

COPY --from=build /app/target/product-api.jar /app/target/

ENTRYPOINT ["java", "-jar", "target/product-api.jar"]