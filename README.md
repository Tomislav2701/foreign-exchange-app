# FEXApp - Currency Conversion API

FEXApp is a Spring Boot application providing endpoints for currency exchange rate lookup, currency conversion, and transaction history. It includes features such as caching, rate limiting, interactive API documentation (Swagger), and is containerized with Docker.

---

## 📋 Features

- **Currency Conversion:** Convert between currencies using real-time exchange rates.
- **Exchange Rate Lookup:** Retrieve the current exchange rate for any two supported currencies.
- **Transaction History:** View a paginated history of conversion transactions with filtering by transaction ID or date.
- **Interactive API Documentation:** Explore and test the API through Swagger UI.
- **Containerization:** Easily deploy the application using Docker.
- **Caching & Rate Limiting:** Improve performance and prevent abuse.

---

## ⚡ Makefile Commands

The project includes a Makefile for convenience. If you have `make` installed (or use Git Bash/WSL on Windows), use these commands in the project root.

### 1. Build the Project

- With Maven

```bash
make build
```
This runs mvnw.cmd clean package on Windows to build the project and produce the JAR file in the target directory.
   
```bash
make run
```
This runs mvnw.cmd spring-boot:run on Windows

- With Docker

```bash
make docker-build
```

This executes:
docker build -t fexapp .
and packages your app into a Docker image named fexapp.

```bash
make docker-run
```

This runs the container (naming it fexapp_container) and maps port 8080 of the container to port 8080 on your host.

```bash
make docker-stop
```
Stops and removes the fexapp_container if it’s running.

---

🔍 API Documentation and H2 console
Swagger UI:

```bash
http://localhost:8080/swagger-ui/index.html
```

H2 Console (for Development):

```bash
http://localhost:8080/h2-console
```
Default Credentials:

-JDBC URL: 

```bash
jdbc:h2:mem:test2701
```
Username: sa

Password: (leave blank)

---

⚙️ Configuration

Environment Variables

Create a .env file in the project root with:

-EXCHANGE_API_KEY=your_api_key_here

This key is used to authenticate with the external exchange rate API - exchangerate.host
