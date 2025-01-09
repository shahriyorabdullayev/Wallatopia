package uz.droid.wallatopia.presentation.screens.contracts

import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.presentation.screens.contracts.HomeContract.Intent

class FavoritesContract {

    data class FavoritesState(
        val favoriteImages: List<ImageUiModel> = emptyList()
    )

    sealed interface Intent {
        data object Init : Intent
        data class DeleteFromFavorites(val imageUiModel: ImageUiModel) : Intent
    }
}