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
  implementation(libs.androidx.navigation.runtime)
  implementation(libs.kotlinx.coroutines.core)
  implementation(platform(libs.androidx.compose.bom))
//  androidTestImplementation(libs.androidx.arch.core.testing)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.runtime)
  androidTestImplementation(libs.androidx.compose.uiTest)
  androidTestImplementation(libs.androidx.compose.uiTestJunit)
  androidTestImplementation(libs.androidx.compose.uiTestManifest)
//  androidTestImplementation(libs.androidx.espresso.contrib)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.androidx.navigation.compose)
  androidTestImplementation(libs.androidx.navigation.runtime)
  androidTestImplementation(libs.kotlinx.coroutines.test)
}

version = property("VERSION_NAME") as String

nmcp {
  publishAllPublications {
    username = findProperty("MAVEN_CENTRAL_USERNAME") as String
    password = findProperty("MAVEN_CENTRAL_PASSWORD") as String
    publicationType = "USER_MANAGED"
  }
}
