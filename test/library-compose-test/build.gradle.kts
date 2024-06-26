plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.gradleMavenPublish)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.nmcp)
}

android {
  namespace = "com.stylianosgakis.navigation.recents.url.sharing"
  compileSdk = 34

  defaultConfig {
    minSdk = 21
    targetSdk = 34
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
  implementation(projects.library.navigationRecentsUrlSharing)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.navigation.runtime)
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.runtime)
  implementation(libs.androidx.compose.uiTest)
  implementation(libs.androidx.compose.uiTestJunit)
  implementation(libs.androidx.compose.uiTestManifest)
  implementation(libs.androidx.espresso.core)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.navigation.runtime)
  implementation(libs.kotlinx.coroutines.test)
}
