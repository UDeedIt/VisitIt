# VisitIt - Project Structure & Architecture

This document outlines the high-level architecture and module structure for the VisitIt project.

## 1. High-Level Architecture

VisitIt is built using a Kotlin Multiplatform (KMP) approach, combined with a Spring Boot backend. The architecture follows Clean Architecture principles, ensuring a separation of concerns across the stack.

The project is structured into three main areas:
1.  **Backend (Server):** Spring Boot application handling business logic, database, and API endpoints.
2.  **Shared Multiplatform Code (Client Core):** Shared business logic, networking, database (offline cache), and view models.
3.  **Client Applications (UI):** Platform-specific UI implementations (Jetpack Compose for Android/Desktop, Compose Multiplatform/SwiftUI for iOS).

## 2. Directory Structure

The project root is organized to support a "monorepo" style, keeping backend and frontend code synchronized.

```text
VisitIt/
├── backend/                  # Spring Boot Server Application
│   ├── build.gradle.kts      # Server-specific build configuration
│   └── src/                  # Server source code (Controllers, Services, Repositories)
│
├── composeApp/               # Cross-platform UI (Jetpack Compose / Compose Multiplatform)
│   ├── build.gradle.kts
│   ├── src/commonMain/       # Shared UI components and screens
│   ├── src/androidMain/      # Android-specific UI configuration
│   ├── src/desktopMain/      # Desktop-specific UI configuration
│   └── src/iosMain/          # iOS-specific UI configuration (if using Compose for iOS)
│
├── shared/                   # KMP Shared Business Logic (Core)
│   ├── build.gradle.kts      # Shared module configuration (SQLDelight, Ktor, Kodein)
│   ├── src/commonMain/       # Shared Domain, Data (Repositories), and Presentation (ViewModels) logic
│   ├── src/androidMain/      # Android-specific actual implementations (e.g., SQLDelight driver)
│   ├── src/iosMain/          # iOS-specific actual implementations
│   └── src/desktopMain/      # Desktop-specific actual implementations
│
├── iosApp/                   # Native iOS Application Entry Point
│   ├── iosApp.xcodeproj      # Xcode project
│   └── iosApp/               # SwiftUI code and iOS app delegate
│
├── gradle/                   # Gradle configuration and Version Catalogs
│   ├── libs.versions.toml    # Centralized dependency management
│   └── wrapper/              # Gradle wrapper files
│
├── docs/                     # Project Documentation
│   └── architecture/         # Architecture decision records and guides
│
├── build.gradle.kts          # Root project build configuration
└── settings.gradle.kts       # Project module inclusion settings