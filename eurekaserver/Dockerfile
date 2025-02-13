FROM gradle:jdk21-corretto-al2023 as build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build ./app/build/libs/*.jar ./eureka.jar
EXPOSE 8761
ENTRYPOINT java -jar eureka.jar
