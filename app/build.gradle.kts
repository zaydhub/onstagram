plugins {
    id("com.android.application")
}

android {
    namespace = "com.onstagram.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.onstagram.app"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.activity:activity:1.9.3")
    implementation("androidx.webkit:webkit:1.12.1")

}
