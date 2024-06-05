# Utiliza una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR de tu aplicación al directorio de trabajo en el contenedor
COPY target/RestCrud-0.0.1-SNAPSHOT.jar /app/RestCrud-0.0.1-SNAPSHOT.jar

# Comando para ejecutar la aplicación cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "RestCrud-0.0.1-SNAPSHOT.jar"]
