# 🏷️ Designation Service

Designation Service is a Spring Boot microservice in the Employee Management System (EMS) ecosystem.

It manages job titles/designations within the organization and provides validation endpoints for other services like Employee Service.

---

## 📌 Overview

The Designation Service is responsible for:

- Managing job designations (CRUD)
- Enforcing unique designation name and code
- Providing a validation endpoint `/check/{id}` used by Employee Service
- Applying global exception handling
- Structured logging with traceId support
- Clean separation of controller, service, and repository layers

This service follows microservices best practices and clean architecture principles.

---

## 🏗 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- ModelMapper
- Spring Validation
- Spring Boot Actuator
- Micrometer (Tracing + MDC logging)
- RESTful API Design

---

## 🗂 Project Structure

```
com.example.designation
│
├── controller
├── service
│ └── impl
├── repository
├── entity
├── dto
├── config
├── exception
└── util
└── constants
└── enums
```
---

## 🏗 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- ModelMapper
- Spring Validation
- Spring Boot Actuator
- Micrometer (Tracing + MDC logging)
- RESTful API Design

```


## 🗄 Database Schema

### 📌 `designations` Table

```sql
CREATE TABLE designations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    code VARCHAR(100) NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## API Endpoints

✅ Create Designation

```
POST /api/v1/designations
```

✅ Get All Designations

```
GET /api/v1/designations
```


✅ Get Designation by ID

```
GET /api/v1/designations/{id}
```

✅ Update Designation

```
PUT /api/v1/designations/{id}
```

✅ Delete Designation

```
DELETE /api/v1/designations/{id}
```


## 🔎 Validation Endpoint (Used by EMS)

```
GET /api/v1/designations/check/{id}
```

Response:
```
{
  "valid": true
}
```

This endpoint is used by Employee Service before assigning a designation to an employee.


##🔄 Inter-Service Communication

Employee Service calls:

```
/api/v1/designations/check/{id}
```

Before:

- Creating an employee

- Updating an employee

This ensures:

- Only valid designation IDs are used

- Data integrity across microservices


## 🛡 Validation & Error Handling

The service implements:

- Unique constraint validation for:

- designation name

- designation code

- Global Exception Handling


Structured error response format:

```
{
  "errorCode": "VALIDATION_FAILED",
  "errorMessage": "Designation code already exists",
  "path": "/api/v1/designations",
  "traceId": "abc123",
  "timestamp": "2025-12-10T12:45:00",
  "httpMethod": "POST"
}
```

## 📊 Logging & Monitoring

- Structured logs with traceId

- Micrometer tracing integration

- Actuator endpoints:

```
- /actuator/health
- /actuator/info
```

## How to Run

1️⃣ Configure Database

```
spring.datasource.url=jdbc:mysql://localhost:3306/designation_service
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

2️⃣ Run Application

```
mvn spring-boot:run
```

Default Port:

```
http://localhost:8082
```


## 🧠 Design Principles

- Single Responsibility (only designation management)

- No business logic leakage outside its domain

- No authentication logic (handled by Auth Service)

- Designed for safe inter-service validation