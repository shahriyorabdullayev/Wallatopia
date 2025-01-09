package uz.droid.wallatopia.presentation.screens.contracts

import uz.droid.wallatopia.domain.model.CategoryUiModel
import uz.droid.wallatopia.domain.model.ImageUiModel

class HomeContract {

    data class HomeState(
        val categories: List<CategoryUiModel> = emptyList(),
        val images: List<ImageUiModel> = emptyList(),
        val favorites: List<ImageUiModel> = emptyList()
    )

    sealed interface Intent {
        data object Init : Intent
        data class AddToFavorites(val imageUiModel: ImageUiModel) : Intent
        data class DeleteFromFavorites(val imageUiModel: ImageUiModel) : Intent
        data class OpenImage(val imageUrl: String) : Intent
    }
}

