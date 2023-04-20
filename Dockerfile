FROM maven:3.8.4-openjdk-17
WORKDIR /app
COPY . /app
RUN mvn clean install -DskipTests
RUN mvn package
ENTRYPOINT ["java", "-jar", "target/authentification.jar"]
