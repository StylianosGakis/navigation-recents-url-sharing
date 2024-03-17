plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "com.stylianosgakis.navigation.recents.url.sharing.sample"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.stylianosgakis.navigation.recents.url.sharing.sample"
    minSdk = 23
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.androidx.composeCompiler.get()
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.compose)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.navigation.runtime)
  implementation(projects.library.navigationRecentsUrlSharing)
}