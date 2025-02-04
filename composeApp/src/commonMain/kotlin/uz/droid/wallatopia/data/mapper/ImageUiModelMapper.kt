package uz.droid.wallatopia.data.mapper

import uz.droid.wallatopia.data.network.response.PixabayImage
import uz.droid.wallatopia.domain.model.ImageUiModel

val PixabayImage.toUiModel: ImageUiModel
    get() = ImageUiModel(
        id = this.id.toString(),
        thumbUrl = this.thumbImageUrl,
        originalUrl = this.originalImageUrl,
        color = "",
    )

fun PixabayImage.toUiModel(
    isFavorite: Boolean
): ImageUiModel {
    return ImageUiModel(
        id = this.id.toString(),
        thumbUrl = this.thumbImageUrl,
        originalUrl = this.originalImageUrl,
        color = "",
        isFavorite = isFavorite
    )
}

