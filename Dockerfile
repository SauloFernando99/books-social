# Usar imagem do JDK
FROM openjdk:21

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR da aplicação para dentro do container
COPY target/books-social-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]