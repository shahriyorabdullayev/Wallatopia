package uz.droid.wallatopia.data.mapper

import uz.droid.wallatopia.data.network.response.PixabayImage
import uz.droid.wallatopia.domain.model.ImageUiModel
import kotlin.random.Random

val PixabayImage.toUiModel: ImageUiModel
    get() = ImageUiModel(
        id = this.id.toString(),
        thumbUrl = this.thumbImageUrl,
        originalUrl = this.originalImageUrl,
        color = getRandomPlaceholderColor(),
        pageURL = pageURL,
        timestamp = 0L
    )

fun PixabayImage.toUiModel(
    isFavorite: Boolean
): ImageUiModel {
    return ImageUiModel(
        id = this.id.toString(),
        thumbUrl = this.thumbImageUrl,
        originalUrl = this.originalImageUrl,
        color = getRandomPlaceholderColor(),
        isFavorite = isFavorite,
        pageURL = pageURL,
        timestamp = 0L
    )
}

fun getRandomPlaceholderColor(): String {
    val random = Random
    return "${random.nextInt(10, 256)}${random.nextInt(10, 256)}${random.nextInt(10, 256)}"
}