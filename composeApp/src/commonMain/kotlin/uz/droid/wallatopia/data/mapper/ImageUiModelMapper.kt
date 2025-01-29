package uz.droid.wallatopia.data.mapper

import uz.droid.wallatopia.data.network.response.PixabayImage
import uz.droid.wallatopia.data.network.response.UnsplashResponse
import uz.droid.wallatopia.domain.model.ImageUiModel

val UnsplashResponse.toUiModel: ImageUiModel
    get() = ImageUiModel(
        id = this.id,
        thumbUrl = this.urls.small,
        originalUrl = this.urls.small,
        height = this.height,
        width = this.width,
        color = this.color,
        blurHash = this.blurHash
    )

fun UnsplashResponse.toUiModel(
    isFavorite: Boolean
): ImageUiModel {
    return ImageUiModel(
        id = this.id,
        thumbUrl = this.urls.small,
        originalUrl = this.urls.small,
        height = this.height,
        width = this.width,
        color = this.color,
        blurHash = this.blurHash,
        isFavorite = isFavorite
    )
}

val PixabayImage.toUiModel: ImageUiModel
    get() = ImageUiModel(
        id = this.id.toString(),
        thumbUrl = this.thumbImageUrl,
        originalUrl = this.originalImageUrl,
        height = 0,
        width = 0,
        color = "",
        blurHash = ""
    )

fun PixabayImage.toUiModel(
    isFavorite: Boolean
): ImageUiModel {
    return ImageUiModel(
        id = this.id.toString(),
        thumbUrl = this.thumbImageUrl,
        originalUrl = this.originalImageUrl,
        height = 0,
        width = 0,
        color = "",
        blurHash = "",
        isFavorite = isFavorite
    )
}

