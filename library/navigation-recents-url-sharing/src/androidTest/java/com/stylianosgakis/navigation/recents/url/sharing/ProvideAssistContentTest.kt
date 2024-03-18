package com.stylianosgakis.navigation.recents.url.sharing

import android.app.assist.AssistContent
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ProvideAssistContentTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun testProvideAssistContentWithDifferentRoutes() {
    lateinit var navController: NavHostController
    composeTestRule.setContent {
      navController = rememberNavController()
      TestComposable(navController)
    }

    val assistContent = AssistContent()
    composeTestRule.onNodeWithText("First").assertExists()
    navController.provideAssistContent(assistContent, allDeepLinkUriPatterns)
    assertEquals("https://sample.app.link/first", assistContent.webUri.toString())

    composeTestRule.runOnUiThread {
      navController.navigate("second/param1/param2")
    }
    composeTestRule.onNodeWithText("First").assertDoesNotExist()
    composeTestRule.onNodeWithText("Second: param1:param1, param2:param2").assertExists()
    navController.provideAssistContent(assistContent, allDeepLinkUriPatterns)
    assertEquals("https://sample.app.link/second/param1/param2", assistContent.webUri.toString())

    composeTestRule.runOnUiThread {
      navController.popBackStack()
    }
    composeTestRule.onNodeWithText("First").assertExists()
    navController.provideAssistContent(assistContent, allDeepLinkUriPatterns)
    assertEquals("https://sample.app.link/first", assistContent.webUri.toString())

    composeTestRule.runOnUiThread {
      navController.navigate("third/param1?param2=param2")
    }
    composeTestRule.onNodeWithText("First").assertDoesNotExist()
    composeTestRule.onNodeWithText("Third: param1:param1, param2:param2").assertExists()
    navController.provideAssistContent(assistContent, allDeepLinkUriPatterns)
    assertEquals("https://sample.app.link/third/param1?param2=param2", assistContent.webUri.toString())
  }
}

@Composable
private fun TestComposable(navHostController: NavHostController) {
  NavHost(
    navController = navHostController,
    startDestination = "first",
  ) {
    composable(
      "first",
      deepLinks = listOf(
        navDeepLink { uriPattern = DeepLink.ScreenFirst }
      ),
    ) {
      BasicText("First")
    }
    composable(
      route = "second/{param1}/{param2}",
      arguments = listOf(
        navArgument("param1") { type = NavType.StringType },
        navArgument("param2") { type = NavType.StringType },
      ),
      deepLinks = listOf(
        navDeepLink { uriPattern = DeepLink.ScreenSecond }
      ),
    ) {
      val param1 = it.arguments?.getString("param1")
      val param2 = it.arguments?.getString("param2")
      BasicText("Second: param1:$param1, param2:$param2")
    }
    composable(
      route = "third/{param1}?param2={param2}",
      arguments = listOf(
        navArgument("param1") { type = NavType.StringType },
        navArgument("param2") { type = NavType.StringType; nullable = true },
      ),
      deepLinks = listOf(
        navDeepLink { uriPattern = DeepLink.ScreenThird }
      )
    ) {
      val param1 = it.arguments?.getString("param1")
      val param2 = it.arguments?.getString("param2")
      BasicText("Third: param1:$param1, param2:$param2")
    }
  }
}

@Suppress("ConstPropertyName")
private object DeepLink {
  const val ScreenFirst = "https://sample.app.link/first"
  const val ScreenSecond = "https://sample.app.link/second/{param1}/{param2}"
  const val ScreenThird = "https://sample.app.link/third/{param1}?param2={param2}"
}

private val allDeepLinkUriPatterns = listOf(
  DeepLink.ScreenFirst,
  DeepLink.ScreenSecond,
  DeepLink.ScreenThird
)
