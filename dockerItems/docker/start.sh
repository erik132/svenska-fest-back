#!/bin/bash
set -e

# 1. Start PostgreSQL in the background.
#    docker-entrypoint.sh is the original postgres image script;
#    it creates the DB / user from the POSTGRES_* env vars on first run.
docker-entrypoint.sh postgres &

# 2. Wait until PostgreSQL accepts connections.
echo "Waiting for PostgreSQL..."
until pg_isready -h localhost -U "$POSTGRES_USER" -d "$POSTGRES_DB" -q; do
  sleep 1
done
echo "PostgreSQL is ready."

# 3. Start Spring Boot in the background.
#    Pass the DB credentials to Spring Boot via environment variables
#    so it uses the same values as PostgreSQL.
echo "Starting Spring Boot application..."
SPRING_DATASOURCE_USERNAME="$POSTGRES_USER" \
SPRING_DATASOURCE_PASSWORD="$POSTGRES_PASSWORD" \
java -jar /app/app.jar &

# 4. Start nginx in the foreground (keeps the container alive).
echo "Starting nginx..."
nginx -g "daemon off;"
