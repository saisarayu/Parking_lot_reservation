# Stage 1: Build
FROM openjdk:17-slim AS build

WORKDIR /workspace
COPY . .

# Give executable permission to mvnw
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the app
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
