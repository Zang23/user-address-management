FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM gvenzl/oracle-xe:21-slim

ENV ORACLE_PASSWORD=123456

# copia jar gerado no build
COPY target/cep-api-0.0.1-SNAPSHOT.jar app.jar

# copia script
COPY start.sh /start.sh

RUN chmod +x /start.sh

EXPOSE 8080

CMD ["/start.sh"]