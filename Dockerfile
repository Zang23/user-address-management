# BUILD
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# RUNTIME
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Oracle Wallet
COPY src/main/resources/wallet /app/wallet
ENV TNS_ADMIN=/app/wallet

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]