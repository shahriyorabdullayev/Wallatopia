package uz.droid.wallatopia.presentation.screens.contracts

import uz.droid.wallatopia.domain.model.HomeCategoryModel
import uz.droid.wallatopia.domain.model.ImageUiModel

class HomeContract {
    data class HomeState(
        val categories: List<HomeCategoryModel> = emptyList(),
        val images: List<ImageUiModel> = emptyList()
    )

    sealed interface Intent {
        data object Init : Intent
        data object NavigateToCategories : Intent
        data object NavigateToAIGenerate : Intent
        data object NavigateToSearch : Intent
        data class AddToFavorite(val imageUiModel: ImageUiModel) : Intent
        data class OpenImage(val imageUrl: String) : Intent
    }
}

