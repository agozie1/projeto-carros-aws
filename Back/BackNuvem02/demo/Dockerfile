# Imagem base
FROM openjdk:17-jdk-slim

# Define /app como o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR para o container no diretório /app
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 25000
EXPOSE 25000

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=25000"]