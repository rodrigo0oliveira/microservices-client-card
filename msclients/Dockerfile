FROM gradle:jdk21-corretto-al2023 as build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build ./app/build/libs/*.jar ./msclients.jar
ARG EUREKA_SERVER=localhost
ENTRYPOINT java -jar msclients.jar