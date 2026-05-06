# 📦 Order Management System

A production-ready Order Management API built with **Spring Boot** and **Hexagonal Architecture (Ports & Adapters)**. Engineered to demonstrate clean code practices, strict domain isolation, framework independence, and enterprise-grade security standards.

[![Java](https://img.shields.io/badge/Java-21-007396?logo=java)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-6DB33F?logo=spring)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-336791?logo=postgresql)](https://www.postgresql.org)
[![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker)](https://www.docker.com)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📖 Overview

This project simulates a corporate order management workflow, featuring authentication, product catalog management, and order lifecycle tracking. It is structured to prioritize **maintainability, testability, and separation of concerns**, making it an ideal reference for modern backend engineering practices.

## 🏗️ Architecture

The system follows the **Hexagonal Architecture (Ports & Adapters)** pattern to strictly decouple business logic from technical frameworks:

```
┌─────────────────────────────────────────────────────────────┐
│                         API Layer                           │
│  (REST Controllers, DTOs, Exception Handlers, Validation)   │
└───────────────┬───────────────────────────────┬─────────────┘
                │ Uses                          │ Uses
┌───────────────▼──────────────┐   ┌────────────▼──────────────┐
│       Infrastructure         │   │        Infrastructure     │
│ (JPA Repositories, Entities) │   │ (JWT, Security Config)    │
│          ↓ Implements        │   │          ↓ Implements     │
└──────────────────────────────┘   └───────────────────────────┘
                │                          │
┌───────────────▼──────────────────────────▼─────────────────┐
│                       Domain Layer                         │
│  (Pure Business Models, Use Cases, Port Interfaces)        │
│              Zero framework dependencies                   │
└────────────────────────────────────────────────────────────┘
```

### 🔑 Core Principles
- **Domain Isolation**: Business rules contain zero imports from Spring, JPA, or HTTP libraries.
- **Dependency Inversion**: Infrastructure adapters implement domain `Ports`, not the other way around.
- **Testability**: Domain logic can be unit-tested with JUnit + Mockito without launching Spring or a database.
- **Technology Agnostic**: Swapping PostgreSQL for MongoDB, or REST for gRPC, requires changes only in the infrastructure layer.

## 🛠️ Tech Stack

| Category          | Technology                          |
|-------------------|-------------------------------------|
| Language          | Java 21                             |
| Framework         | Spring Boot 4.x                     |
| Security          | Spring Security + JWT (jjwt)        |
| Persistence       | Spring Data JPA + PostgreSQL 17     |
| Build Tool        | Maven                               |
| Documentation     | SpringDoc OpenAPI 3 (Swagger UI)    |
| Containerization  | Docker + Docker Compose             |
| Testing           | JUnit 5, Mockito, AssertJ           |

## ✨ Key Features

- 🔐 **JWT Authentication**: Stateless login, role-based access control (`USER` / `ADMIN`)
- 📦 **Product Management**: CRUD with validation, pagination, and optimistic locking ready
- 🧾 **Order Lifecycle**: Create orders, manage items, status flow (`PENDING` → `PAID` → `SHIPPED` / `CANCELED`)
- 🌐 **RESTful API**: Standardized JSON responses, global exception handling, CORS configured
- 📖 **Live Documentation**: Auto-generated Swagger UI at `/swagger-ui.html`
- 🐳 **Zero-Config Environment**: `docker-compose` for database and application
- 🧪 **Testable Design**: Domain unit tests run without Spring context or database

## 📂 Project Structure

```text
src/main/java/com/viacode/order_management/
├── api/                 # Controllers, DTOs, Exception Handlers
├── domain/              # Pure business logic, Models, Ports, Use Cases
│   ├── auth/            # User, Role, AuthUseCase, AuthPort
│   ├── product/         # Product, ProductUseCase, ProductPort
│   └── order/           # Order, OrderItem, OrderUseCase, OrderPort
└── infrastructure/      # Framework integrations & Adapters
    ├── persistence/     # JPA Entities, Repositories, Domain ↔ Entity mappers
    ├── security/        # JWT service, Security filters, Config
    └── config/          # Cross-cutting concerns (CORS, Swagger, etc.)
```

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Docker & Docker Compose
- Maven (`./mvnw` wrapper included)

### 🐳 Run with Docker (Recommended)
```bash
# Start PostgreSQL and the application
docker-compose up --build -d

# View logs
docker-compose logs -f order-management
```

### 💻 Run Locally
1. Ensure PostgreSQL is running on `localhost:5432` (credentials in `application.yml`)
2. Run the application:
```bash
./mvnw spring-boot:run
```

## 📖 API Documentation

Once running, access the interactive documentation:
🔗 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🧪 Testing Strategy

- **Domain Tests**: JUnit + Mockito. No `@SpringBootTest`. Fast, isolated, framework-agnostic.
- **Adapter Tests**: Verify JPA mapping, repository queries, and JWT generation.
- **API Tests**: `@WebMvcTest` + `MockMvc` for endpoint contracts and validation.

Run all tests:
```bash
./mvnw test
```

## 🗺️ Roadmap

- [ ] React frontend with JWT auth flow & protected routes
- [ ] Event-driven architecture (RabbitMQ/Kafka for order status notifications)
- [ ] CI/CD pipeline (GitHub Actions + Docker registry)
- [ ] Observability (Spring Boot Actuator, Prometheus, Grafana)
- [ ] Multi-tenancy support

## 📄 License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

---
💡 *Built with clean architecture principles. Questions or feedback? Open an issue or reach out via [viniibp.dev@gmail.com / [linkedin](https://www.linkedin.com/in/vinicius-batista-dev/)].*
