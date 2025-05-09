# Usa una imagen base de Java
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Instala el cliente de MySQL
RUN apt update && apt install -y default-mysql-client

# Copia el archivo JAR generado al contenedor
COPY target/cloud_proyecto1-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8001

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]