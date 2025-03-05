FROM maven:3.9-eclipse-temurin-23 AS builder

COPY . .
RUN mvn clean install

FROM eclipse-temurin:23-jdk

COPY --from=builder target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT java -jar /app.jar
