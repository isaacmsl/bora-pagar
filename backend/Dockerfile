FROM eclipse-temurin:21

WORKDIR /app

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

CMD ["./mvnw", "spring-boot:run"]