# Etapa 1: Build do projeto com Maven
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto pro container
COPY . .

# Executa o build do Maven (gera o JAR)
RUN mvn clean package -DskipTests

# Etapa 2: Runtime com o JDK
FROM openjdk:21

WORKDIR /app

# Copia o JAR gerado da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
