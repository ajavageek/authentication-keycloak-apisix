FROM maven:3.9.3-eclipse-temurin-17-focal as build

COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src src

RUN --mount=type=cache,target=/root/.m2,rw mvn package -DskipTests

FROM eclipse-temurin:17-jre-jammy

COPY --from=build target/myapp-1.0.jar myapp.jar

ENTRYPOINT [ "java", "-jar", "myapp.jar"]
