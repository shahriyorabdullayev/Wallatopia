package uz.droid.wallatopia

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import uz.droid.wallatopia.common.theme.WallatopiaAppTheme
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.domain.model.ImageUiModelNavType
import uz.droid.wallatopia.presentation.navigation.HomeNavGraph
import uz.droid.wallatopia.presentation.screens.CategoryDetailsScreen
import uz.droid.wallatopia.presentation.screens.ImageDetailsScreen
import uz.droid.wallatopia.presentation.screens.ImageGenerateScreen
import uz.droid.wallatopia.presentation.screens.LanguageScreen
import uz.droid.wallatopia.presentation.screens.NewGeneratedImageId
import uz.droid.wallatopia.presentation.screens.PrivacyPolicyScreen
import uz.droid.wallatopia.presentation.screens.SearchScreen
import uz.droid.wallatopia.presentation.screens.SplashScreen
import uz.droid.wallatopia.presentation.screens.TermsAndConditionsScreen
import uz.droid.wallatopia.presentation.viewmodels.SettingsViewModel
import kotlin.reflect.typeOf

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransition = staticCompositionLocalOf<SharedTransitionScope?> { null }
val LocalAnimatedVisibility = staticCompositionLocalOf<AnimatedVisibilityScope?> { null }

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun App() {
    KoinContext {
        WallatopiaAppTheme {
            val settingsViewModel: SettingsViewModel = koinViewModel()
            val state = settingsViewModel.uiState.collectAsStateWithLifecycle()
            changeLang(state.value.language)
            val navController = rememberNavController()

            SharedTransitionLayout {
                CompositionLocalProvider(LocalSharedTransition provides this) {
                    HavHostMain(navController = navController)
                }
            }
        }
    }
}

@Composable
fun HavHostMain(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screens.SplashScreen,
        enterTransition = {
            fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(300))
        }
    ) {
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
            CompositionLocalProvider(LocalAnimatedVisibility provides this) {
                HomeNavGraph(
                    globalNavController = navController
                )
            }
        }
        composable<Screens.CategoryDetailsScreen> { backStackEntry ->
            val category: Screens.CategoryDetailsScreen = backStackEntry.toRoute()
            CompositionLocalProvider(LocalAnimatedVisibility provides this) {
                CategoryDetailsScreen(
                    categoryName = category.categoryName,
                    onBackPressed = navController::popBackStack,
                    navigateToImageDetails = { imageUiModel ->
                        navController.navigate(
                            Screens.ImageDetailsScreen(
                                imageUiModel
                            )
                        )
                    }
                )
            }
        }
        composable<Screens.SearchScreen> {
            SearchScreen(
                onBackPressed = navController::popBackStack,
                navigateToCategoryDetails = {
                    navController.navigate(Screens.CategoryDetailsScreen(it))
                },
                navigateToImageDetails = { imageUiModel ->
                    navController.navigate(
                        Screens.ImageDetailsScreen(
                            imageUiModel
                        )
                    )
                }
            )
        }
        composable<Screens.ImageGenerateScreen> {
            ImageGenerateScreen(
                onBackPressed = navController::popBackStack,
                navigateToImageDetails = { generatedImageUrl ->
                    val imageUiModel = ImageUiModel(
                        id = NewGeneratedImageId,
                        thumbUrl = generatedImageUrl,
                        originalUrl = generatedImageUrl,
                        timestamp = currentTimeInMilliSeconds,
                        isAiGenerated = true
                    )
                    navController.navigate(
                        Screens.ImageDetailsScreen(
                            imageUiModel
                        )
                    )
                }
            )
        }

        composable<Screens.LanguageScreen> {
            LanguageScreen(
                onBackPressed = navController::popBackStack
            )
        }

        composable<Screens.TermsScreen> {
            TermsAndConditionsScreen(
                onBackPressed = navController::popBackStack
            )
        }

        composable<Screens.PrivacyScreen> {
            PrivacyPolicyScreen(
                onBackPressed = navController::popBackStack
            )
        }

        composable<Screens.ImageDetailsScreen>(
            typeMap = mapOf(typeOf<ImageUiModel>() to ImageUiModelNavType),
        ) { backStackEntry ->
            val imageUiModel = backStackEntry.toRoute<Screens.ImageDetailsScreen>().imageUiModel
            CompositionLocalProvider(LocalAnimatedVisibility provides this) {
                ImageDetailsScreen(
                    imageUiModel = imageUiModel,
                    onBackPressed = navController::popBackStack
                )
            }
        }
    }
}
