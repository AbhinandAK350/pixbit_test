# Pixbit Test

This is an Android application built using modern Android development practices. The app allows
users to register, log in, and view a list of employees. It also has features to add new employees
and view employee details.

## Features

* **User Registration:** New users can create an account.
* **User Login:** Existing users can log in to their accounts.
* **Employee List:** View a paginated list of employees.
* **Add Employee:** Add a new employee to the list.
* **Employee Details:** View detailed information about a specific employee.

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern with a clean architecture
approach. The code is organized into feature modules.

* **`core`:** Contains the base classes and utilities used across the app, including networking,
  navigation, and theme.
* **`register`:** Handles user registration.
* **`login`:** Manages user login.
* **`home`:** Displays the list of employees.
* **`add_employee`:**  Handles the addition of new employees.
* **`profile_details`:** Shows the details of an employee.

## Tech Stack

* **[Kotlin](https://kotlinlang.org/)**: The programming language used.
* **[Jetpack Compose](https://developer.android.com/jetpack/compose)**: For building the UI.
* **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)**: To manage
  UI-related data in a lifecycle-conscious way.
* **[Hilt](https://dagger.dev/hilt/)**: For dependency injection.
* **[Retrofit](https://square.github.io/retrofit/)**: For making HTTP requests to the API.
* **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)**: For JSON
  serialization and deserialization.
* **[Coil](https://coil-kt.github.io/coil/)**: For image loading.
* **[DataStore](https://developer.android.com/topic/libraries/architecture/datastore)**: For storing
  simple key-value pairs.
* **[Navigation 3](https://developer.android.com/guide/navigation/navigation-3)**: For navigating
  between screens.

## API

The app communicates with a backend API with the base URL:
`https://machine-test-cn1pkjyu.on-forge.com/api/`.

## Build

To build the app, you can use Android Studio or the following Gradle command:

```bash
./gradlew assembleDebug
```
