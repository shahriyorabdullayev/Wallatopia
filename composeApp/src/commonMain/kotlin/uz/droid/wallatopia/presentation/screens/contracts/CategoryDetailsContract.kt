package uz.droid.wallatopia.presentation.screens.contracts

import uz.droid.wallatopia.domain.model.ImageUiModel

class CategoryDetailsContract {
    data class CategoryDetailsState(
        val categoryPhotos: List<ImageUiModel> = emptyList()
    )

    sealed interface Intent {
        data class Init(val categoryId: String) : Intent
        data class AddToFavorite(val imageUiModel: ImageUiModel) : Intent
    }
}