package com.stylianosgakis.navigation.recents.url.sharing.sample

import android.app.assist.AssistContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.stylianosgakis.navigation.recents.url.sharing.provideAssistContent

class MainActivity : ComponentActivity() {
  private var navController: NavController? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navHostController = rememberNavController()
      LifecycleStartEffect(navHostController) {
        navController = navHostController
        onStopOrDispose {
          navController = null
        }
      }
      SampleApp(navHostController)
    }
  }

  override fun onProvideAssistContent(outContent: AssistContent) {
    super.onProvideAssistContent(outContent)
    navController?.provideAssistContent(outContent, allDeepLinkUriPatterns)
  }
}

@Composable
fun SampleApp(navHostController: NavHostController, modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize()) {
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
        Column(
          verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.fillMaxSize(),
        ) {
          BasicText("First")
          Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(
              Modifier.background(Color.Gray).clickable { navHostController.navigate("second/param1/param2") }
            ) {
              BasicText("Second", Modifier.padding(16.dp))
            }
            Box(
              Modifier.background(Color.Gray).clickable { navHostController.navigate("third/param1?param2=param2") }
            ) {
              BasicText("Third", Modifier.padding(16.dp))
            }
          }
        }
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
        BasicText(
          "Second: param1:$param1, param2:$param2",
          Modifier
            .fillMaxSize()
            .wrapContentSize()
        )
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
        BasicText(
          "Third: param1:$param1, param2:$param2",
          Modifier
            .fillMaxSize()
            .wrapContentSize()
        )
      }
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
