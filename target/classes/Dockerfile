# Use Java 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy project files
COPY . .

# Build the project
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Copy jar file
COPY target/*.jar app.jar

# Run app
ENTRYPOINT ["java","-jar","app.jar"]