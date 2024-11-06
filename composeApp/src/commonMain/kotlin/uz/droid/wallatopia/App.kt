package uz.droid.wallatopia

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import uz.droid.wallatopia.presentation.navigation.HomeNavGraph
import uz.droid.wallatopia.presentation.screens.SplashScreen


@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            HavHostMain()
        }
    }
}

@Composable
fun HavHostMain(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen) {
        composable<Screens.SplashScreen> {
            SplashScreen(
                navigateToHome = {
                    navController.navigate(Screens.HomeGraph) {
                        popUpTo(Screens.SplashScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Screens.HomeGraph> {
            HomeNavGraph()
        }
    }
}
