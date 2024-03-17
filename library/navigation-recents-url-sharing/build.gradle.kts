plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.nmcp)
  `maven-publish`
}

android {
  namespace = "com.stylianosgakis.navigation.recents.url.sharing"
  compileSdk = 34

  defaultConfig {
    minSdk = 21
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation(libs.androidx.navigation.runtime)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}

version = property("VERSION_NAME") as String

nmcp {
  publishAllPublications {
    username = findProperty("MAVEN_CENTRAL_USERNAME") as String
    password = findProperty("MAVEN_CENTRAL_PASSWORD") as String
    publicationType = "USER_MANAGED"
  }
}
