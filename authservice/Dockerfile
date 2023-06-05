# FROM openjdk:17
# ARG JAR_FILE=authservice/target/authservice-0.0.1-SNAPSHOT.jar
# COPY ${JAR_FILE} authservice.jar
# ENTRYPOINT ["java","-jar","authservice.jar"]

FROM maven:3.9.1 as build
ARG SERVICE_NAME=authservice
WORKDIR /app
COPY ${SERVICE_NAME}/pom.xml .
COPY ${SERVICE_NAME}/src ./src
RUN mvn package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

