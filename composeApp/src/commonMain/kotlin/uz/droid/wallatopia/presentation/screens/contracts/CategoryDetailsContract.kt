package uz.droid.wallatopia.presentation.screens.contracts

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import uz.droid.wallatopia.domain.model.ImageUiModel

class CategoryDetailsContract {
    data class CategoryDetailsState(
        val categoryPhotos: Flow<PagingData<ImageUiModel>> = emptyFlow()
    )

    sealed interface Intent {
        data class LoadCategoryImages(val categoryName: String) : Intent
        data class AddToFavorite(val imageUiModel: ImageUiModel) : Intent
    }
}