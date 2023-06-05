FROM maven:3.9.1 as build
ARG SERVICE_NAME=boardgameservice
WORKDIR /app
COPY ${SERVICE_NAME}/pom.xml .
COPY ${SERVICE_NAME}/src ./src
RUN mvn package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

