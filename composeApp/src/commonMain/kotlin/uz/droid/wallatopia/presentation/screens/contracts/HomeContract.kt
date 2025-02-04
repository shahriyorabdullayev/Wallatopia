package uz.droid.wallatopia.presentation.screens.contracts

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import uz.droid.wallatopia.domain.model.CategoryUiModel
import uz.droid.wallatopia.domain.model.ImageUiModel

class HomeContract {

    data class HomeState(
        val selectedTabIndex: Int = 0,
        val categories: List<CategoryUiModel> = emptyList(),
        val images: List<ImageUiModel> = emptyList(),
        val favorites: List<ImageUiModel> = emptyList(),
        val aiGeneratedImages: List<ImageUiModel> = emptyList(),
        val homeImages: Flow<PagingData<ImageUiModel>> = emptyFlow(),
    )

    sealed interface Intent {
        data object Init : Intent
        data class AddToFavorites(val imageUiModel: ImageUiModel) : Intent
        data class DeleteFromFavorites(val imageUiModel: ImageUiModel) : Intent
        data class OpenImage(val imageUrl: String) : Intent
        data class SelectTab(val index: Int) : Intent
    }
}

