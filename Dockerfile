# =========================
# ETAPA 1 - BUILD
# =========================
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copia arquivos de build
COPY pom.xml .
COPY src ./src

# Faz build do projeto
RUN mvn clean package -DskipTests


# =========================
# ETAPA 2 - RUNTIME
# =========================
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia o JAR gerado
COPY --from=build /app/target/*.jar app.jar

# =========================
# ORACLE WALLET (IMPORTANTE)
# =========================
# se você colocar o wallet dentro do projeto:
COPY src/main/resources/wallet /app/wallet

# aponta o Oracle JDBC para o wallet
ENV TNS_ADMIN=/app/wallet

# =========================
# PORTA DO RENDER
# =========================
EXPOSE 8080

# =========================
# START DA APLICAÇÃO
# =========================
ENTRYPOINT ["java", "-jar", "app.jar"]