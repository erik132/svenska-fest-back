# ============================================================
# Stage 1: Build React frontend
# ============================================================
FROM node:22-alpine AS frontend-build

WORKDIR /app
COPY svenska_fest/package*.json ./
RUN npm install
COPY svenska_fest/ ./

# Remove the hardcoded backend URL so that API calls become
# relative requests (e.g. "/events/...") — nginx will proxy them
RUN sed -i 's|http://localhost:3015||g' src/main.jsx

RUN npm run build

# ============================================================
# Stage 2: Build Spring Boot backend
# ============================================================
FROM maven:3.9-eclipse-temurin-21 AS backend-build

WORKDIR /app
COPY svenska-fest-back/pom.xml ./
RUN mvn dependency:go-offline -B
COPY svenska-fest-back/src ./src
RUN mvn package -DskipTests

# ============================================================
# Stage 3: Final image — postgres + Java + nginx in one container
# ============================================================
FROM postgres:latest

# --- Install nginx ---
RUN apt-get update \
    && apt-get install -y --no-install-recommends nginx \
    && rm -rf /var/lib/apt/lists/*

# --- Copy JRE 21 from the Maven build stage ---
COPY --from=backend-build /opt/java/openjdk /opt/java/openjdk
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# --- Copy React production build into nginx web root ---
COPY --from=frontend-build /app/dist /var/www/html

# --- Copy Spring Boot fat JAR ---
COPY --from=backend-build /app/target/svenska-fest-back-*.jar /app/app.jar

# --- Copy configuration files ---
COPY docker/nginx.conf /etc/nginx/sites-available/default
COPY docker/start.sh /start.sh
RUN chmod +x /start.sh

# --- PostgreSQL environment (used by the postgres entrypoint) ---
# POSTGRES_USER and POSTGRES_PASSWORD must be passed at runtime:
#   docker run -e POSTGRES_USER=... -e POSTGRES_PASSWORD=...
ENV POSTGRES_DB=svenska_fest

# Only expose port 80 (nginx). PostgreSQL 5432 and Spring Boot 3015
# stay internal to the container.
EXPOSE 80

# Override the postgres image's default entrypoint so we can
# start all three services ourselves.
ENTRYPOINT []
CMD ["/bin/bash", "/start.sh"]
