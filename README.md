# Clean Architecture Task Management

A Task Management API built with Spring Boot, Java 25, MySQL, Flyway, and a Clean Architecture-inspired package structure.

## Prerequisites

- [JDK 25](https://adoptium.net/temurin/releases/?version=25) for local Gradle builds
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) to run the application stack and Testcontainers tests

Verify the Java installation:

```bash
java -version
```

The output must report Java 25. Gradle uses the wrapper included in this repository, so a separate Gradle installation is not needed.

## Run the full stack

The quickest way to run the application and MySQL together is Docker Compose:

```bash
docker compose up --build
```

This starts:

| Service | Address | Notes |
| --- | --- | --- |
| Application | `http://localhost:8080` | Built from the local source using Java 25 |
| MySQL 8.4 | `localhost:3306` | Database name: `task_management` |

The application waits until MySQL passes its health check before starting. Stop the stack with:

```bash
docker compose down
```

MySQL data is retained in the `mysql-data` Docker volume. To stop the stack **and permanently delete local database data**:

```bash
docker compose down --volumes
```

## Run the application locally

Start only MySQL through Compose:

```bash
docker compose up -d mysql
```

Then start Spring Boot from your terminal. These environment variables override the container-only connection values and point the application at the MySQL port exposed on your machine:

```bash
export SPRING_DATASOURCE_URL='jdbc:mysql://localhost:3306/task_management'
export SPRING_DATASOURCE_USERNAME='task_management'
export SPRING_DATASOURCE_PASSWORD='task_management'
./gradlew bootRun
```

The application is available at `http://localhost:8080`. Flyway automatically applies migrations from `src/main/resources/db/migration` on startup. The first migration creates the `tasks` table.

When finished, stop MySQL:

```bash
docker compose down
```

## Configuration

Spring Boot accepts its standard datasource environment variables. The Docker Compose defaults are intended for local development only.

| Variable | Compose value |
| --- | --- |
| `SPRING_DATASOURCE_URL` | `jdbc:mysql://mysql:3306/task_management` |
| `SPRING_DATASOURCE_USERNAME` | `task_management` |
| `SPRING_DATASOURCE_PASSWORD` | `task_management` |

Inside Compose, the hostname is `mysql`; when running the application on your host machine, use `localhost` instead.

## Build and test

Build the application:

```bash
./gradlew build
```

Run all tests:

```bash
./gradlew test
```

Docker Desktop must be running for the full test suite. `TaskManagementApplicationTests` uses Testcontainers to launch an isolated MySQL 8.4 instance; no manually started database is required. Domain and use-case tests, such as `CreateTaskServiceTest`, are pure unit tests and do not start Spring Boot, MySQL, JPA, or HTTP.

Run one test class:

```bash
./gradlew test --tests com.budhalabs.taskmanagement.application.usecase.CreateTaskServiceTest
```

## Code formatting

Google Java Format is enforced through Spotless:

```bash
./gradlew spotlessCheck
./gradlew spotlessApply
```

Use `spotlessCheck` in CI and `spotlessApply` to automatically format the Java source files.

## Project layout

```text
src/main/java/.../
├── domain/          # Entities, domain rules, exceptions, repository ports
├── application/     # Use cases and application services
└── infrastructure/  # JPA entities and persistence mapping

src/main/resources/db/migration/
└── V1__create_tasks_table.sql
```

The project currently provides the core task domain and persistence building blocks. HTTP endpoints can be added as infrastructure adapters without coupling the domain or use cases to Spring MVC.
