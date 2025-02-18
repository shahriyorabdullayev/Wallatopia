package uz.droid.wallatopia.presentation.screens.contracts

import uz.droid.wallatopia.domain.model.ImageUiModel

val initialImageModel = ImageUiModel(
    id = "",
    thumbUrl = "",
    originalUrl = "",
    timestamp = 0L
)

class ImageDetailsContract {
    data class ImageDetailsState(
        val isLoading: Boolean = false,
        val imageUiModel: ImageUiModel = initialImageModel
    )

    sealed interface Intent {
        data class Init(val imageUiModel: ImageUiModel) : Intent
        data object HandleInFavorites : Intent
    }
}