package uz.droid.wallatopia

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import uz.droid.wallatopia.common.theme.WallatopiaAppTheme
import uz.droid.wallatopia.presentation.navigation.HomeNavGraph
import uz.droid.wallatopia.presentation.screens.SplashScreen


@Composable
@Preview
fun App() {
    KoinContext {
        WallatopiaAppTheme {
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
