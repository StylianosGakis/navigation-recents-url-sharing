## Navigation Recents URL Sharing

[![GitHub license](https://img.shields.io/github/license/saltstack/salt)](https://raw.githubusercontent.com/StylianosGakis/navigation-recents-url-sharing/3207ccfdd231b549c0e999b3d00bea3beae67d3f/LICENSE.txt)
[![Maven Central](https://img.shields.io/maven-central/v/com.stylianosgakis.navigation.recents.url.sharing/navigation-recents-url-sharing)](https://repo1.maven.org/maven2/com/stylianosgakis/navigation/recents/url/sharing/navigation-recents-url-sharing/)

This library provides easy integration with the Android OS "Recents" screen by automatically
figuring out if you are in a screen which has a deep link, and provides that deep link as the
copyable/shareable link using the [URL Sharing](https://developer.android.com/guide/components/activities/recents#url-sharing)
feature.

It relies on you using [androidx.navigation](https://developer.android.com/jetpack/androidx/releases/navigation) and 
using that library's integration with deep links to handle all of your deep links.

[This article](https://dev.to/gakisstylianos/enable-recents-url-sharing-using-androidxnavigation-1189) covers how this library came to be, and some of the implementation details.

## Getting started

1. Add the `com.stylianosgakis.navigation.recents.url.sharing:navigation-recents-url-sharing:{VERSION_HERE}` 
dependency.
2. Use the `onProvideAssistContent(outContent: AssistContent)` function in your Activity, and use 
the library's `fun NavController.provideAssistContent(outContent: AssistContent, allDeepLinkUriPatterns: List<String>)` function.
In your Activity:
```kotlin
override fun onProvideAssistContent(outContent: AssistContent) {
  super.onProvideAssistContent(outContent)
  navController?.provideAssistContent(outContent, listOfAllDeepLinkUriPatterns)
}
```
You can take a look at the [sample app](sample/app/src/main/java/com/stylianosgakis/navigation/recents/url/sharing/sample/MainActivity.kt) 
for a full example.

## Releases

The latest version is [![Maven Central](https://img.shields.io/maven-central/v/com.stylianosgakis.navigation.recents.url.sharing/navigation-recents-url-sharing)](https://repo1.maven.org/maven2/com/stylianosgakis/navigation/recents/url/sharing/navigation-recents-url-sharing/)

Releases are hosted on [Maven Central](https://repo1.maven.org/maven2/com/stylianosgakis/navigation/recents/url/sharing/navigation-recents-url-sharing/).

```kotlin
repositories {
  mavenCentral()
}

dependencies {
  implementation("com.stylianosgakis.navigation.recents.url.sharing:navigation-recents-url-sharing:{VERSION_HERE}")
}
```

### Real world example

This library is currently used in the [Hedvig](https://github.com/HedvigInsurance/android/blob/ea27562b34e5231a579bb56d3e0461339646c0b6/app/app/src/main/kotlin/com/hedvig/app/feature/loggedin/ui/LoggedInActivity.kt#L268-L274) app.
![The Hedvig app showing a deep link to ](resources/Hedvig_app_recents_integration.png)

### Future work

When `androidx.navigation` properly provides a KMP version, this library will try to provide integration with that 
too if the deep link integration is ported to multiplatform too.