FROM maven:3.9.7-eclipse-temurin-21 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .
RUN mvn package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/AnimalsBuild.jar ./AnimalsBuild.jar

CMD ["java", "-jar", "./AnimalsBuild.jar"]
