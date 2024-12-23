plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "ir.androidcoder.traktmovies"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = "ir.androidcoder.traktmovies"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        renderscriptTargetApi  = 19
        renderscriptSupportModeEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "BASE_URL_TMDB", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "AUTHORIZATION_TMDB", "\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4N2E0MDAyZTM2ZGJjMjgxZDlhNTA4MTkwMzQzNjMxMSIsIm5iZiI6MTcxMTYxNDAzMC40OTQwMDAyLCJzdWIiOiI2NjA1Mjg0ZWVjYWVmNTAxN2FhZmU3MGIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.cv2-xhrEYwZujwzSydww_0yI1P1L4u3zm55YI4-Puyo\"")

            buildConfigField("String", "BASE_URL_Trakt", "\"https://api.trakt.tv/\"")
            buildConfigField("String", "CLIENT_ID", "\"f60163d7fb20530ffd2b98a8c37ca9a882bcfae897be992f408a5bf0073e5f8b\"")
            buildConfigField("String", "CLIENT_SECRET", "\"60cf187b6332ad18cdb9ee209464f0d1185d9cbcab3a66f96942e21f39447aeb\"")
            buildConfigField("String", "REDIRECT_URL", "\"movieshosein://zapp.com/code\"")
        }

        debug {

            buildConfigField("String", "BASE_URL_TMDB", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "AUTHORIZATION_TMDB", "\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4N2E0MDAyZTM2ZGJjMjgxZDlhNTA4MTkwMzQzNjMxMSIsIm5iZiI6MTcxMTYxNDAzMC40OTQwMDAyLCJzdWIiOiI2NjA1Mjg0ZWVjYWVmNTAxN2FhZmU3MGIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.cv2-xhrEYwZujwzSydww_0yI1P1L4u3zm55YI4-Puyo\"")

            buildConfigField("String", "BASE_URL_Trakt", "\"https://api.trakt.tv/\"")
            buildConfigField("String", "CLIENT_ID", "\"f60163d7fb20530ffd2b98a8c37ca9a882bcfae897be992f408a5bf0073e5f8b\"")
            buildConfigField("String", "CLIENT_SECRET", "\"60cf187b6332ad18cdb9ee209464f0d1185d9cbcab3a66f96942e21f39447aeb\"")
            buildConfigField("String", "REDIRECT_URL", "\"movieshosein://zapp.com/code\"")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(project(":data"))
    implementation(project(":domain"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt
    implementation ("com.google.dagger:hilt-android:2.50")
    kapt ("com.google.dagger:hilt-compiler:2.50")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")

    // Lifecycle components
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    //Room
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")

    //Dot
    implementation("com.tbuonomo:dotsindicator:5.1.0")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    //paging
    implementation("androidx.paging:paging-runtime:3.3.2")
    implementation("androidx.room:room-paging:2.6.1")

    //youtube
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.30")

}