# Pixbit Android Machine Test

Pixbit is a modern Android application developed as part of a technical assessment. The project demonstrates end-to-end Android development practices, including authentication, paginated data handling, form submissions, and a modular clean architecture setup.

The application was built entirely from scratch based on the provided requirements and designs, with a strong focus on scalability, maintainability, and modern Android standards.

---

## 📱 Application Overview

The app allows users to:

- Register and authenticate
- View a paginated list of employees
- Add new employees
- View detailed employee profiles

---

## ✨ Features

### Authentication
- User Registration with validation
- User Login with persisted session handling

### Employee Management
- Employee List with pagination
- Add Employee using form-based submission
- Employee Details screen with full profile information

---

## 🏗 Architecture

The project follows **MVVM with Clean Architecture**, ensuring a clear separation of concerns and testable components.

### Module Structure

- **core**
  - Networking configuration
  - Dependency injection
  - Navigation setup
  - Theme and design system
  - Shared utilities
- **register**
  - User registration flow
- **login**
  - Authentication and session management
- **home**
  - Employee listing and pagination
- **add_employee**
  - Employee creation flow
- **profile_details**
  - Employee detail view

Each feature module is self-contained and communicates through well-defined interfaces.

---

## 🧰 Tech Stack

- Kotlin
- Jetpack Compose
- ViewModel and StateFlow
- Hilt
- Retrofit
- Kotlinx Serialization
- Coil
- DataStore
- Navigation 3

---

## 🌐 API Configuration

Base URL:

https://machine-test-cn1pkjyu.on-forge.com/api/


The base URL is configured in the networking layer and can be easily replaced for different environments.

---

## 🛠 Build Instructions

Build the debug APK using:

```bash
./gradlew assembleDebug
