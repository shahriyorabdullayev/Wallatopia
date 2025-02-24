package uz.droid.wallatopia

import kotlinx.serialization.Serializable
import uz.droid.wallatopia.domain.model.ImageUiModel

object Screens {

    @Serializable
    object SplashScreen

    @Serializable
    data class CategoryDetailsScreen(val categoryName: String)

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
    data class ImageDetailsScreen(
        val imageUiModel: ImageUiModel
    )

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