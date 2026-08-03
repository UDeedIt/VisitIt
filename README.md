# VisitIt 🛠️📱

**VisitIt** is an enterprise-grade, offline-first Field Operations & Dynamic Inspection Platform built with **Kotlin Multiplatform (KMP)** and a **Spring Boot** backend deployed on **AWS**.

Technicians use VisitIt to perform site audits, equipment inspections, and safety compliance checks in zero-connectivity environments (basements, remote industrial sites). The app leverages **Server-Driven UI (SDUI)** to dynamically render inspection forms sent from the backend without requiring app store updates.

---

## 🏗️ Tech Stack (Plan)

### Client Side (Kotlin Multiplatform)
* **UI Framework:** Compose Multiplatform (Android, iOS, Desktop)
* **Local Database:** [SQLDelight](https://sqldelight.github.io/sqldelight/) — Offline-first relational SQLite engine with compile-time SQL verification.
* **Dependency Injection:** [Kodein DI](https://kosi-libs.org/kodein-di/) — Modular container injection featuring scoped `subDI` containers for inspection lifecycles.
* **Networking:** Ktor Client + `kotlinx.serialization`
* **Concurrency:** Kotlin Coroutines & Flow

### Backend Side (JVM / Server)
* **Framework:** Spring Boot (Kotlin)
* **Security:** Spring Security (JWT / OAuth2 authentication)
* **Database Access:** Spring Data JPA / Hibernate (connected to AWS RDS PostgreSQL)
* **REST & Shared Contracts:** Consumes shared Kotlin DTOs and validation rules directly from the KMP core module.

### Cloud & Infrastructure (AWS)
* **Compute:** AWS ECS Fargate (Containerized Spring Boot Server)
* **Database:** AWS RDS PostgreSQL
* **Media Storage:** AWS S3 (High-resolution inspection photo evidence)
* **Security & Network:** AWS CloudFront, API Gateway, IAM Roles

---

## 🏛️ System Architecture

```text
                               ┌─────────────────────────────────────────┐
                               │        VisitIt KMP Client App           │
                               │   (Compose Android, iOS, Desktop)       │
                               └────────────────────┬────────────────────┘
                                                    │
                                           (Kodein DI Container)
                                                    │
                          ┌─────────────────────────┴─────────────────────────┐
                          │                                                   │
                          ▼                                                   ▼
             [ SQLDelight Database ]                             [ Network Layer (Ktor) ]
          (Offline-First Local Storage &                       (Fetch Forms, Sync Deltas)
            Cached Dynamic SDUI Schemas)                                      │
                          ▲                                                   │
                          └─────────────────────────┬─────────────────────────┘
                                                    │ REST / JWT (Offline Queue Sync)
                                                    ▼
                               ┌─────────────────────────────────────────┐
                               │        Spring Boot Backend Engine       │
                               │   (Form Schemas, Auth, PDF Generation)  │
                               └────────────────────┬────────────────────┘
                                                    │
                               ┌────────────────────┴────────────────────┐
                               │                                         │
                               ▼                                         ▼
                     [ AWS RDS PostgreSQL ]                        [ AWS S3 ]
                   (Central Enterprise DB)                 (Photo/Video Audit Evidence)