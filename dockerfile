# Fase de construcción con Maven
FROM --platform=linux/arm/v7 maven:3.8.7-eclipse-temurin-17-focal AS build
WORKDIR /app

# Copiar primero el POM para aprovechar la caché de Docker
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente
COPY src src

# Empaquetar la aplicación
RUN mvn package -DskipTests

# Fase de ejecución
FROM --platform=linux/arm/v7 eclipse-temurin:17-jre-focal
WORKDIR /app

# Crear usuario no-root para mayor seguridad
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Copiar el JAR construido
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto y configurar tiempo
EXPOSE 8080
ENV TZ=America/Mexico_City

# Entrada point optimizado para Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]