# Étape de build
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape de production
FROM openjdk:17
COPY --from=build /app/target/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]