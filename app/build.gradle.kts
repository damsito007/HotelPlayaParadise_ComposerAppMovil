plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.hotelplayaparadise"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hotelplayaparadise"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    val nav_version = "2.8.0"

    implementation ("androidx.navigation:navigation-compose:$nav_version")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // https://mvnrepository.com/artifact/com.github.kittinunf.fuel/fuel
    implementation("com.github.kittinunf.fuel:fuel:3.0.0-alpha03")
// https://mvnrepository.com/artifact/com.github.kittinunf.fuel/fuel-android
    implementation("com.github.kittinunf.fuel:fuel-android:2.3.1")
// https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.11.0")
// https://mvnrepository.com/artifact/io.coil-kt/coil-compose
    implementation("io.coil-kt:coil-compose:2.7.0")
// https://mvnrepository.com/artifact/com.github.kittinunf.fuel/fuel-coroutines
    implementation("com.github.kittinunf.fuel:fuel-coroutines:2.3.1")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    // https://mvnrepository.com/artifact/io.coil-kt/coil-compose
    implementation("io.coil-kt:coil-compose:2.7.0")


}