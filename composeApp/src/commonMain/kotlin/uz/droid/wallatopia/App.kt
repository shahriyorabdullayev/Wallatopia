package uz.droid.wallatopia

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import uz.droid.wallatopia.common.theme.WallatopiaAppTheme
import uz.droid.wallatopia.presentation.navigation.HomeNavGraph
import uz.droid.wallatopia.presentation.screens.CategoryDetailsScreen
import uz.droid.wallatopia.presentation.screens.ImageGenerateScreen
import uz.droid.wallatopia.presentation.screens.LanguageScreen
import uz.droid.wallatopia.presentation.screens.PrivacyPolicyScreen
import uz.droid.wallatopia.presentation.screens.SearchScreen
import uz.droid.wallatopia.presentation.screens.SplashScreen
import uz.droid.wallatopia.presentation.screens.TermsAndConditionsScreen
import uz.droid.wallatopia.presentation.viewmodels.SettingsViewModel


@Composable
@Preview
fun App() {
    KoinContext {
        WallatopiaAppTheme {
            val settingsViewModel: SettingsViewModel = koinViewModel()
            val state = settingsViewModel.uiState.collectAsStateWithLifecycle()
            changeLang(state.value.language)
            val navController = rememberNavController()

            HavHostMain(navController = navController)
        }
    }
}

@Composable
fun HavHostMain(navController: NavHostController) {
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
            HomeNavGraph(
                globalNavController = navController
            )
        }
        composable<Screens.CategoryDetailsScreen> { backStackEntry ->
            val category: Screens.CategoryDetailsScreen = backStackEntry.toRoute()
            CategoryDetailsScreen(
                categoryId = category.categoryId,
                onBackPressed = navController::popBackStack
            )
        }
        composable<Screens.SearchScreen> {
            SearchScreen(
                onBackPressed = navController::popBackStack,
                navigateToCategoryDetails = {
                    navController.navigate(Screens.CategoryDetailsScreen(it))
                }
            )
        }
        composable<Screens.ImageGenerateScreen> {
            ImageGenerateScreen(
                onBackPressed = navController::popBackStack
            )
        }

        composable<Screens.LanguageScreen> {
            LanguageScreen(
                onBackPressed = navController::popBackStack
            )
        }

        composable<Screens.TermsScreen> {
            TermsAndConditionsScreen()
        }

        composable<Screens.PrivacyScreen> {
            PrivacyPolicyScreen()
        }
    }
}
