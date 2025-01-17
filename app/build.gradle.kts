plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "dam.pmdm.tarea_03_rmlp"
    compileSdk = 34

    defaultConfig {
        applicationId = "dam.pmdm.tarea_03_rmlp"
        minSdk = 24
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding= true
    }
}

dependencies {
    implementation(libs.firebase.bom)
    implementation(libs.firebase.bom.v3200)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.analytics)
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")
    implementation ("com.google.firebase:firebase-auth:23.1.0") // O la última versión
    implementation ("com.google.android.gms:play-services-auth:20.4.0")// O la última versión
    implementation(libs.core.splashscreen)
    implementation(libs.firebase.ui.auth)
    implementation(libs.navigation.ui.v283)
    implementation(libs.navigation.fragment.v283)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)
    implementation(libs.cardview)
    implementation(libs.picasso )
    implementation(libs.recyclerview)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.annotation)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}