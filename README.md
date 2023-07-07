# MyGitHub

MyGitHub Is an application for accessing GitHub accounts by providing Username, ID, Followers and Following information as well as a search feature based on username, The application uses the API from GitHub

## Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Dependencies](#dependencies)

## Introduction

MayGitHub is developed as an Android application using the Kotlin programming language. It leverages various Android libraries and tools to provide a smooth user experience for browsing and searching GitHub repositories.

## Project Structure

The project follows a standard Android project structure. Here are the main directories and files:

- `app` directory: Contains the main source code and resources for the application.
  - `src/main` directory: Contains the main source code and resources.
    - `AndroidManifest.xml`: Defines the application's components and configuration.
    - `java/com/project/mygithub`: Contains the Kotlin source code files for the application.
    - `res`: Contains the app resources, including layouts, strings, and drawables.

- `build.gradle`: The project-level Gradle build script.

- `app/build.gradle`: The module-level Gradle build script for the app.

## Configuration

The application is configured using the `build.gradle` files. Here are some of the important configurations:

- `applicationId`: The package name of the application.

- `minSdk`: The minimum Android SDK version required to run the application.

- `targetSdk`: The target Android SDK version the application is built against.

- `versionCode`: The version code of the application.

- `versionName`: The version name of the application.

- `buildConfigField`: Custom build configuration field for storing sensitive data (e.g., API keys). Note that the value in this README is a placeholder and should be replaced with a valid key.

- `buildTypes`: The build types configuration, including release and debug builds.

- `buildFeatures/viewBinding`: Enables View Binding for the application.

- `compileOptions`: Java compatibility options for the project.

- `kotlinOptions/jvmTarget`: The JVM target version for Kotlin.

## Dependencies

MayGitHub utilizes various libraries and dependencies to provide the desired functionality. Here are some of the key dependencies used:

- `androidx.recyclerview:recyclerview`: RecyclerView library for displaying lists of data.

- `androidx.cardview:cardview`: CardView library for creating Material Design cards.

- `androidx.room:room-ktx`: Room library for database management (Kotlin extensions).

- `androidx.room:room-runtime`: Room library for database management (runtime).

- `androidx.datastore:datastore-preferences`: DataStore library for managing key-value data.

- `androidx.lifecycle:lifecycle-viewmodel-ktx`: ViewModel library with Kotlin extensions.

- `androidx.lifecycle:lifecycle-livedata-ktx`: LiveData library with Kotlin extensions.

- `com.google.android.material:material`: Material Components library for modern UI components.

- `androidx.viewpager2:viewpager2`: ViewPager2 library for creating swipeable screens.

- `com.intuit.sdp:sdp-android`: Scalable Size Unit library for creating responsive layouts.

- `androidx.fragment:fragment-ktx`: Fragment library with Kotlin extensions.

- `com.squareup.okhttp3:logging-interceptor`: Logging interceptor for OkHttp client.

- `de.hdodenhof:circleimageview`: CircleImageView library for displaying circular images.

- `com.github.bumptech.glide:glide`: Glide library for image loading and caching.

- `com.squareup.retrofit2:retrofit`: Retrofit library for making HTTP requests.

- `com.squareup.retrofit2:converter-gson`: Gson converter for Retrofit.

- `androidx.core:core-ktx`: AndroidX core library with Kotlin extensions.

- `androidx.appcompat:appcompat`: AppCompat library for backwards compatibility.

- `androidx.constraintlayout:constraintlayout`: ConstraintLayout library for flexible layouts.

- `junit:junit`: JUnit library for unit testing.

- `androidx.test.ext:junit`: JUnit library for Android instrumentation tests.

- `androidx.test.espresso:espresso-core`: Espresso library for UI testing.

Please refer to the `app/build.gradle` file for the specific versions of the dependencies used in the project.

**Note:** Ensure that you have the necessary dependencies and versions configured correctly in the Gradle files to successfully build and run the MayGitHub application.

## Conclusion

This README provides an overview of the MayGitHub Android application, including its project structure, configuration, and dependencies. You can refer to the relevant files and documentation to further explore the code and customize the application as needed.
