package uz.droid.wallatopia

import kotlinx.serialization.Serializable

object Screens {

    @Serializable
    object SplashScreen

    @Serializable
    data class CategoryDetailsScreen(val categoryId: String)

    @Serializable
    object SearchScreen

    @Serializable
    object ImageGenerateScreen

    @Serializable
    object LanguageScreen

    @Serializable
    object PrivacyScreen

    @Serializable
    object TermsScreen

    @Serializable
    data class ImageDetailsScreen(val thumbUrl: String, val originalUrl: String)

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