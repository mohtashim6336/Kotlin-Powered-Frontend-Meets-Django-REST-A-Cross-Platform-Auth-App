plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.companyauthapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.companyauthapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0" // Use the appropriate version
        kotlinCompilerVersion = "1.8.20" // Make sure this matches the Kotlin version you're using
    }
    buildFeatures {
        viewBinding = true
        dataBinding=true
        compose = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // Jetpack Compose dependencies
    implementation ("androidx.compose.ui:ui:1.5.0") // or the latest version
    implementation ("androidx.compose.material:material:1.5.0") // or the latest version
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.0") // for preview support

    // Required for using Compose with ViewModel, LiveData, etc.
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation ("androidx.activity:activity-compose:1.7.0")
    implementation ("androidx.compose.ui:ui:1.5.0") // or the latest version
    implementation ("androidx.compose.material3:material3:1.0.0") // Add this for Material3 components
    implementation ("androidx.compose.foundation:foundation:1.5.0") // Foundation components
    implementation ("androidx.compose.material:material:1.5.0") // Material components (if needed)

    // Other necessary dependencies for Compose and tooling
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.0") // For preview
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation ("androidx.activity:activity-compose:1.7.0")
}

