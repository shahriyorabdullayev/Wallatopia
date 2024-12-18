package uz.droid.wallatopia.data.mapper

import uz.droid.wallatopia.data.entities.UnsplashImageEntity
import uz.droid.wallatopia.domain.model.ImageUiModel

val ImageUiModel.toEntity: UnsplashImageEntity
    get() = UnsplashImageEntity(
        id = this.id,
        imageUrl = this.url,
        blurHash = this.blurHash
    )

val UnsplashImageEntity.toModel: ImageUiModel
    get() = ImageUiModel(
        id = this.id,
        url = this.imageUrl,
        blurHash = this.blurHash,
        isFavorite = true
    )