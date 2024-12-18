package uz.droid.wallatopia.domain.model

data class ImageUiModel(
    val id: String,
    val url: String,
    val blurHash: String,
    val isFavorite: Boolean = false
)
