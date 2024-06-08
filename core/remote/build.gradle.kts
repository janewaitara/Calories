import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("calories.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mumbicodes.remote"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    // return empty string incase the key is not set
    val key: String = gradleLocalProperties(rootDir, providers).getProperty("key") ?: ""

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "key", key)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Ktor
    implementation(libs.bundles.ktor)
    testImplementation(libs.ktor.client.mock)

    // coroutines
    implementation(libs.kotlinx.coroutines)
    testImplementation(libs.kotlinx.coroutines.test)

    testImplementation(libs.junit)
}