package uz.droid.wallatopia.domain.model

data class ImageUiModel(
    val id: String,
    val thumbUrl: String,
    val originalUrl: String,
    val color: String = "",
    val isFavorite: Boolean = false,
    val isAiGenerated: Boolean = false,
)
