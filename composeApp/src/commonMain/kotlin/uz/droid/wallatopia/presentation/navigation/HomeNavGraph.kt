package uz.droid.wallatopia.presentation.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.resources.painterResource
import uz.droid.wallatopia.Screens
import uz.droid.wallatopia.presentation.components.advancedShadow
import uz.droid.wallatopia.presentation.screens.CategoryDetailsScreen
import uz.droid.wallatopia.presentation.screens.SearchScreen
import uz.droid.wallatopia.presentation.screens.home.CategoryScreen
import uz.droid.wallatopia.presentation.screens.home.FavoriteScreen
import uz.droid.wallatopia.presentation.screens.home.HomeScreen
import uz.droid.wallatopia.presentation.screens.home.ProfileScreen

@Composable
fun HomeNavGraph(
    globalNavController: NavController
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationCustom(navController)
        },
        modifier = Modifier.fillMaxSize(),
    ) { scaffoldpadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeGraph.HomeScreen,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            composable<Screens.HomeGraph.HomeScreen> {
                HomeScreen(
                    navigateToSearch = {
                        globalNavController.navigate(Screens.SearchScreen)
                    },
                    navigateToCategories = {
                        navController.navigate(Screens.HomeGraph.CategoriesScreen)
                    },
                    navigateToImageGenerate = {
                        globalNavController.navigate(Screens.ImageGenerateScreen)
                    }
                )
            }
            composable<Screens.HomeGraph.CategoriesScreen> {
                CategoryScreen(
                    navigateToCategoryDetails = {
                        globalNavController.navigate(Screens.CategoryDetailsScreen(it))
                    },
                    onBackPressed = navController::popBackStack
                )
            }
            composable<Screens.HomeGraph.FavouritesScreen> {
                FavoriteScreen()
            }
            composable<Screens.HomeGraph.ProfileScreen> {
                ProfileScreen()
            }
        }
    }
}


@Composable
fun BottomNavigationCustom(navController: NavHostController) {
    val bottomScreens = remember {
        listOf(
            BottomScreens.Home,
            BottomScreens.Categories,
            BottomScreens.Favorites,
            BottomScreens.Profile
        )
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
            .advancedShadow(
                shape = RectangleShape,
                offsetY = (-9).dp,
                blur = 18.dp
            ),
        backgroundColor = Color(0xFF262626)
    ) {
        bottomScreens.forEach { screen ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.route == screen.route::class.qualifiedName } == true
            BottomNavigationItem(
                modifier = Modifier
                    .navigationBarsPadding(),
                selected = isSelected,
                icon = {
                    Icon(
                        painter = painterResource(screen.icon),
                        contentDescription = screen.name,
                        modifier = Modifier.size(32.dp),
                    )
                },
                label = {
                    Text(
                        text = screen.name,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500)
                        )
                    )
                },
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Color(0xFF00AD9F),
                unselectedContentColor = Color(0xFF545454)
            )
        }
    }

}