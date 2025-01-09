package uz.droid.wallatopia.data.mapper

import uz.droid.wallatopia.data.entities.UnsplashImageEntity
import uz.droid.wallatopia.data.network.response.UnsplashResponse
import uz.droid.wallatopia.domain.model.ImageUiModel

val UnsplashResponse.toUiModel: ImageUiModel
    get() = ImageUiModel(
        id = this.id,
        url = this.urls.small,
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
        url = this.urls.small,
        height = this.height,
        width = this.width,
        color = this.color,
        blurHash = this.blurHash,
        isFavorite = isFavorite
    )
}

