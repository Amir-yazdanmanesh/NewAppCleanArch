![Language](https://img.shields.io/github/languages/top/cortinico/kotlin-android-template?color=blue&logo=kotlin)

This is a modern sample project that demonstrates how to build scalable and maintainable Android applications by leveraging Jetpack Compose and Kotlin's latest features. It integrates Compose with the MVI architecture and highlights the use of key Android tools like Kotlin Flow, Hilt for dependency injection, and Room for local data caching.

## Features

* **UI Components**
    * Built entirely with [Jetpack Compose](https://developer.android.com/jetpack/compose), Android's native declarative UI toolkit.
    * Styled with [Material Design](https://material.io/design) guidelines for consistent and engaging user interfaces.

* **Technologies**
    * [Kotlin](https://kotlinlang.org/) used across the project, ensuring concise and expressive code.
    * Asynchronous data handling with [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) and [Flow](https://developer.android.com/kotlin/flow).
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android/) for streamlined dependency injection.
    * [Retrofit](https://square.github.io/retrofit/) for network calls.
    * Image loading with [Coil](https://github.com/coil-kt/coil).
    * **[Room](https://developer.android.com/training/data-storage/room)** for efficient local database caching and offline-first functionality.
    * Paging 3 for handling large data sets.
* Unit tests.
* UI compose tests.

* **Architecture**
    * Single Activity design utilizing [Jetpack Navigation](https://developer.android.com/guide/navigation).
    * MVI (Model-View-Intent) architecture for unidirectional data flow and clear separation of concerns.
    * Android Architecture Components ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)) to handle state and lifecycle-aware components.
    * [KTX Extensions](https://developer.android.com/kotlin/ktx) for simplified Kotlin code in Jetpack.

* **Caching**
    * Integrated **Room** database for offline-first functionality and persistent data storage.
    * Fetches and caches data locally from the Room database, reducing unnecessary network calls.

* **Real-Time Updates**
    * Fetches new data from the remote service every 2 minutes.
    * Compares the new data with the current news list and sends notifications to the user about the number of new articles added.

## App Architecture Overview

The app follows the MVI architectural pattern, separating concerns into distinct layers for better maintainability and scalability. Each screen is driven by states, actions, and effects.

**Architecture layers:**
- **View**: Compose-based UI that listens for state updates and user events.
- **ViewModel**: Handles logic, processes user actions, and updates the view state.
- **Model**: Responsible for fetching and providing data, encapsulating business logic in repositories. Room handles local caching.

### Core Components:
1. **State** - A data class representing the UI's state (e.g., loading status, data to display).
2. **Event** - A user-triggered action passed from the UI to the ViewModel (e.g., button clicks).
3. **Effect** - One-off events like navigation actions, displaying a SnackBar, or showing a Toast.

These components work together to implement a clear, unidirectional data flow. The ViewModel mediates between the view and model, ensuring separation of concerns and predictable behavior.
