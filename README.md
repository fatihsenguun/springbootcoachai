# 🚀 CoachAI - AI-Powered Fitness Backend API

![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)
![Spring AI](https://img.shields.io/badge/Spring%20AI-LLM%20Integration-red.svg)
![JWT](https://img.shields.io/badge/Security-JWT-black.svg)

**CoachAI** is a robust, AI-driven backend API designed to generate and manage hyper-personalized fitness programs. It leverages Large Language Models (LLMs) via `AiService`, secured endpoints using JWT, and a scalable, layered architecture to deliver real-time workout plans.

---

## 📱 Mobile Application
This repository contains the Spring Boot backend API. To view the React Native mobile application that consumes this API and see the UI/Screenshots, visit the frontend repository:
👉 **[CoachAI Mobile App (React Native)](https://github.com/fatihsenguun/reactnativecoachai)**

---

## 📡 API Endpoints

### 🔐 Authentication
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/register` | Register a new user | ❌ |
| POST | `/authenticate` | Login & receive JWT and Refresh tokens | ❌ |
| POST | `/refresh_token` | Refresh access token | ❌ |

### 👤 User & Profile
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/user` | Get current user info & status | ✅ |
| GET | `/fitness_profile` | Get user's physical metrics and goals | ✅ |
| POST | `/fitness_profile/save` | Update or create fitness profile | ✅ |

### 🏋️‍♂️ Workout Programs & Sessions
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/program/generate` | Trigger AI to generate a 30-day program | ✅ |
| GET | `/program/get` | Get the user's active workout program | ✅ |
| GET | `/session/set_completed`| Mark a specific workout session as finished | ✅ |

---

## 📦 Response Structure

All API responses follow a unified wrapper format to ensure consistent parsing on the mobile client.

### Success Response (Authentication Example)
```json
{
  "result": true,
  "errorMessage": null,
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYWx3...",
    "refreshToken": "1d1d3dca-f618-4be2-95ec-09143e628984"
  }
}
```

### Success Response (Workout Program Example)
```json
{
  "result": true,
  "errorMessage": null,
  "data": {
    "name": "Bodyweight Strength & Muscle Program",
    "startDate": "2026-03-10",
    "endDate": "2026-04-09",
    "active": true,
    "sessions": [
      {
        "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "dayNumber": 1,
        "name": "Upper Body Strength",
        "smallTips": "Focus on a tight, stable core...",
        "scheduledDate": "2026-03-10",
        "completed": true,
        "exercises": [
          {
            "name": "Push-up",
            "targetSets": 3,
            "targetReps": 12,
            "startWeightKg": 0,
            "targetWeightKg": 0,
            "restDurationSeconds": 90
          }
        ]
      }
    ]
  }
}
```

### Error Response
```json
{
  "result": false,
  "errorMessage": {
    "status": 400,
    "exception": {
      "hostName": "coachai-server",
      "path": "/program/generate",
      "createTime": "2026-03-10T09:30:13.658Z",
      "message": "Invalid fitness profile data."
    }
  },
  "data": null
}
```

---

## 🏗️ Architecture & Design Decisions

### 1. AI Integration (`AiService.java`)
The core feature of the application. It takes the user's `FitnessProfile`, constructs a highly specific prompt, and communicates with an LLM. It maps the AI's structured JSON output directly into standard Java DTOs (`DtoWorkoutProgram`) while safely handling hallucinated UUIDs using plain Strings.

### 2. Global Exception Handling (`GlobalExceptionHandler.java`)
Centralized error handling using `@RestControllerAdvice`. It catches application-specific exceptions (like `BaseException`, `RateLimitException`) and wraps them in a standardized `RootResponseEntity`, ensuring the mobile app never crashes from unhandled server errors.

### 3. JWT Security & Auth Flow (`JwtAuthenticationFilter.java`)
Stateless security architecture. Access tokens secure standard endpoints, while a database-backed `RefreshToken` system allows users to stay logged into the mobile app seamlessly without re-entering credentials.

### 4. API Rate Limiting (`RateLimitingFilter.java`)
Protects the AI endpoints and authentication routes from abuse and rapid consecutive requests, preserving server resources and LLM quota limits.

### 5. Data Mapping (`IGlobalMapper.java`)
Uses MapStruct to cleanly decouple internal Database Entities (e.g., `WorkoutSession`, `Exercise`) from the Data Transfer Objects (DTOs) sent over the network, ensuring sensitive database fields are never exposed to the client.

---

## 🛠️ Tech Stack

| Layer | Technology                |
|-------|---------------------------|
| Language | Java 17+                  |
| Framework | Spring Boot 3.x           |
| Database | PostgreSQL                |
| Security | Spring Security + JWT     |
| AI Integration | Spring AI (Google Gemini) |
| Mapping | MapStruct                 |
| Containerization | Docker & Docker Compose   |

---

## 📁 Project Structure

```text
src/main/java/com/fatihsengun/
├── config/          # Spring Security, OpenAPI, and Rate Limit configurations
├── controller/      # REST API Controllers (Interfaces & Implementations)
├── dto/             # Data Transfer Objects for API requests/responses
├── entity/          # JPA Database Entities (User, WorkoutProgram, Exercise, etc.)
├── enums/           # RoleType, DayType
├── exception/       # Custom Exception definitions (BaseException, RateLimitException)
├── handler/         # Global Exception Handler
├── jwt/             # JWT Utilities, Filters, and Entry Points
├── mapper/          # MapStruct Global Mapper definitions
├── repository/      # Spring Data JPA Repositories
├── service/         # Business Logic (AiService, WorkoutProgramService, etc.)
└── starter/         # Spring Boot Application Main Class
```

---

## 🚀 Getting Started

### Prerequisites
- Docker & Docker Compose
- Java 17 or higher
- Maven

### Installation

1. **Clone the repository:**
   ```bash
    git clone [https://github.com/fatihsenguun/springbootcoachai.git](https://github.com/fatihsenguun/springbootcoachai.git)
   ```

2. **Start the Database:**
   Use the provided docker-compose file to spin up PostgreSQL.
   ```bash
   docker-compose up -d
   ```

3. **Configure the Environment:**
   Open `src/main/resources/application.properties` and add your Database credentials and AI Provider API Keys.

4. **Run the Application:**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **API Documentation:**
   Once running, view the Swagger UI documentation at:
   `http://localhost:8080/swagger-ui/index.html`