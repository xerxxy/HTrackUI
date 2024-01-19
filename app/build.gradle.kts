plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0-Beta2"
    id("org.jetbrains.kotlin.kapt")

}

android {
    namespace = "com.raul.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.raul.myapplication"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")

    //lifecycle dependencies + view model
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3-android:1.2.0-alpha03")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.23.0")
    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    val kotlinxConverterVersion = "1.0.0"
    val retrofitVersion = "2.9.0"
    val kotlinxSerializationVersion = "1.5.1"

    //Retrofit
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$kotlinxConverterVersion")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
    //// implementation("io.coil-kt:coil-compose:2.4.0")  I currently don't need it
    implementation("com.squareup.okhttp3:okhttp:4.7.2")

    val coroutinesTestingVersion = "1.7.1"
    //Coroutines testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestingVersion")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")

    //Room
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    kapt("androidx.room:room-compiler:$room_version")


    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    //Swipe to refresh
    implementation("com.google.accompanist:accompanist-swiperefresh:0.27.0")




}