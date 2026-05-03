# syntax=docker/dockerfile:1

ARG MODULE=gateway

FROM eclipse-temurin:21-jdk-jammy AS build

ARG MODULE
WORKDIR /workspace

COPY settings.gradle.kts build.gradle.kts gradle.properties ./
COPY gradle ./gradle
COPY gradlew ./gradlew
COPY eureka-server ./eureka-server
COPY gateway ./gateway
COPY service1 ./service1
COPY service2 ./service2

RUN chmod +x ./gradlew \
    && ./gradlew ":${MODULE}:bootJar" --no-daemon -x test \
    && JAR="$(ls "${MODULE}/build/libs"/*.jar | grep -v plain | head -n 1)" \
    && cp "${JAR}" /workspace/app.jar

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /workspace/app.jar ./app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
