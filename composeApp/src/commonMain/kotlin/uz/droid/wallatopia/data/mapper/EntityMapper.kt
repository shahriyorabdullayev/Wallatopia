package uz.droid.wallatopia.data.mapper

import uz.droid.wallatopia.data.entities.UnsplashImageEntity
import uz.droid.wallatopia.domain.model.ImageUiModel

val ImageUiModel.toEntity: UnsplashImageEntity
    get() = UnsplashImageEntity(
        id = this.id,
        imageUrl = this.thumbUrl,
        blurHash = this.blurHash
    )

val UnsplashImageEntity.toUiModel: ImageUiModel
    get() = ImageUiModel(
        id = this.id,
        thumbUrl = this.imageUrl,
        originalUrl = this.imageUrl,
        blurHash = this.blurHash,
        isFavorite = true
    )