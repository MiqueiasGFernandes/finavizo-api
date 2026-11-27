# Finavizo API

[Português (Brasil)](README.md) · **English (US)**

Finavizo is a personal finance API project. This initial stage provides user registration for people who will access the project. The Spring Boot API checks whether the email address is already registered and saves the user in PostgreSQL with a BCrypt password hash.

## Run locally

You need JDK 25 and Docker with Docker Compose. Run the commands from the project root using the included Maven Wrapper. On Windows, use `.\mvnw.cmd` in place of `./mvnw`.

**1. Configure the database.** Create a `.env` file in the project root with the values below. Choose a password for local development. The database name in `DB_URL` must match `POSTGRES_DB`.

```dotenv
POSTGRES_DB=finavizo_dev
POSTGRES_USER=finavizo_dev
POSTGRES_PASSWORD=replace-with-a-local-password
DB_URL=jdbc:postgresql://localhost:5432/finavizo_dev
```

**2. Start PostgreSQL.** Compose exposes the database on port `5432`.

```bash
docker compose up -d db
```

**3. Start the API.** Once PostgreSQL is ready to accept connections, run the command below. Spring Boot reads the `.env` file and starts the server at `http://localhost:8080`. JPA schema generation is enabled.

```bash
./mvnw spring-boot:run
```

## Register a user

In another terminal, send a request to `POST /api/v1/auth/signup`. Use the email address as the `username`.

```bash
curl -i -X POST http://localhost:8080/api/v1/auth/signup \
  -H 'Content-Type: application/json' \
  -d '{"name":"Alex Silva","username":"alex@example.com","password":"Example42!"}'
```

Successful registration returns **HTTP 201 Created**. The response contains `id`, `name`, `username`, `password`, `enabled`, and `createdAt`. The `password` field contains the BCrypt hash. New accounts have `enabled` set to `false`.

## Test and build

With PostgreSQL running, run the integration test. It registers a user in the database configured by `DB_URL`, checks for HTTP 201, and compares the password in the response with the one in the request.

```bash
./mvnw test
```

To run the tests and build the JAR in `target/`, use the package command.

```bash
./mvnw package
```
