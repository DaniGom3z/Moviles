plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.dani.proyecto"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dani.proyecto"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14" // Usar la versión correcta
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Dependencias básicas
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Dependencias de Jetpack Compose
    implementation("androidx.compose.ui:ui:1.7.6") // Actualiza la versión si hay una más reciente
    implementation("androidx.compose.material:material:1.7.6")
    implementation("androidx.compose.material3:material3:1.3.1")

    implementation("androidx.compose.ui:ui-tooling-preview:1.7.6")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.10.0")

    // Para herramientas de depuración y previsualización
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.6")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.7.6")

    // Dependencia de navegación con Compose (si la usas)
    implementation("androidx.navigation:navigation-compose:2.8.5")

    // Dependencias para Retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Dependencias para pruebas
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.compose.ui:ui:1.7.6") // Use the appropriate latest version
    implementation("androidx.compose.material:material:1.7.6")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.6")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.10.0")
    implementation ("androidx.navigation:navigation-compose:2.8.5")
    implementation ("androidx.navigation:navigation-compose:2.8.5")
// Asegúrate de tener la versión más reciente


}
