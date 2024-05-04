FROM eclipse-temurin:21 AS base
WORKDIR /opt/app
EXPOSE 80
FROM maven:3.9.6-eclipse-temurin-21 AS compile
WORKDIR /opt/src/MovieStream_Q71MBS
COPY pom.xml .
RUN mvn compile
FROM compile AS package
COPY ./src ./src
RUN mvn package
FROM base AS final
COPY --from=package /opt/src/hellomicro.backend/target/MovieStream_Q71MBS-0.0.1-SNAPSHOT-exec.jar .
CMD ["java", "-jar", "/opt/app/MovieStream_Q71MBS-0.0.1-SNAPSHOT-exec.jar"]