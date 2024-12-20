package uz.droid.wallatopia.domain.model

data class ImageUiModel(
    val id: String,
    val url: String,
    val blurHash: String,
    val width: Int = 0,
    val height: Int = 0,
    val color: String = "",
    val isFavorite: Boolean = false
)
