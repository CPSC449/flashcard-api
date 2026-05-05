# ---------- Stage 1: Build ----------
FROM maven:3.9-eclipse-temurin-21-alpine

WORKDIR /app

# Copy pom.xml first (cache dependencies)
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copy source
COPY src ./src

# Build jar
RUN mvn clean package -DskipTests -q


# ─── Stage 2: Runtime ──────────────────
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]