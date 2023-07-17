#FROM eclipse-temurin
#WORKDIR /app
#COPY target/springsecsection9-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["sh", "-c", "java -jar app.jar"]

## Build
FROM eclipse-temurin as build
WORKDIR /build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw clean package -DskipTests
FROM eclipse-temurin
WORKDIR /app
COPY --from=build /build/target/springsecsection9-1.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
