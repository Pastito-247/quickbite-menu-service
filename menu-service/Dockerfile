# Etapa 1: Construcción (Build)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compilamos el proyecto saltando las pruebas para que sea más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copiamos el archivo .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]