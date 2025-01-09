package uz.droid.wallatopia.presentation.navigation

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import uz.droid.wallatopia.Screens
import uz.droid.wallatopia.common.resources.Drawables

@Serializable
sealed class BottomScreens<T>(
    val name: String,
    @Contextual val icon: DrawableResource,
    val route: T
) {
    @Serializable
    data object Home : BottomScreens<Screens.HomeGraph.HomeScreen>(
        name = "",
        icon = Drawables.Icons.Home,
        route = Screens.HomeGraph.HomeScreen
    )

    @Serializable
    data object Categories : BottomScreens<Screens.HomeGraph.CategoriesScreen>(
        name = "",
        icon = Drawables.Icons.Category,
        route = Screens.HomeGraph.CategoriesScreen
    )

    @Serializable
    data object Favorites : BottomScreens<Screens.HomeGraph.FavouritesScreen>(
        name = "",
        icon = Drawables.Icons.Favorite,
        route = Screens.HomeGraph.FavouritesScreen
    )

    @Serializable
    data object Profile : BottomScreens<Screens.HomeGraph.ProfileScreen>(
        name = "",
        icon = Drawables.Icons.Profile,
        route = Screens.HomeGraph.ProfileScreen
    )
}