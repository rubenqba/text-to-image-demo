FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /usr/src/app

# Copy pom.xml and download dependencies
COPY . .
RUN --mount=type=cache,target=/root/.m2 ./mvnw package -DskipTests

# Second stage: Lightweight debian-slim image
FROM eclipse-temurin:21-jre-alpine

RUN apk add --no-cache libc6-compat bash curl

WORKDIR /app

# Copy the native binary from the build stage
COPY --from=build /usr/src/app/target/*.jar /app/text-to-image-demo.jar

# Run the application
ADD docker/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
RUN apk add --no-cache bash curl tini
ENTRYPOINT ["/sbin/tini", "--", "/entrypoint.sh"]
