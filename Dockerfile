# Etapa 1: Build do projeto com Maven
# Etapa 1: Build do projeto com Maven
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para dentro do container
COPY . .

# Executa o build do Maven (gera o JAR)
RUN mvn clean package -DskipTests

# Etapa 2: Runtime com o JDK
FROM eclipse-temurin:21-jdk

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
