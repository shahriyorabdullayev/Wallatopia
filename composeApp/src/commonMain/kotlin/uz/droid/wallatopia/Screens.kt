package uz.droid.wallatopia

import kotlinx.serialization.Serializable

object Screens {

    @Serializable
    object SplashScreen

    @Serializable
    object HomeGraph {
        @Serializable
        object HomeScreen

        @Serializable
        object CategoriesScreen

        @Serializable
        object FavouritesScreen

        @Serializable
        object ProfileScreen
    }
}