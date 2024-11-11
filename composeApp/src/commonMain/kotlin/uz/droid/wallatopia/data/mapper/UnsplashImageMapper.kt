package uz.droid.wallatopia.data.mapper

import uz.droid.wallatopia.data.entities.UnsplashImageEntity
import uz.droid.wallatopia.data.network.response.UnsplashResponse
import uz.droid.wallatopia.domain.model.UnsplashImage

val UnsplashResponse.toEntity: UnsplashImageEntity
    get() = UnsplashImageEntity(
        imageUrl = this.urls.regular
    )


val UnsplashImage.toEntity: UnsplashImageEntity
    get()  = UnsplashImageEntity(
        imageUrl = this.url,
    )

val UnsplashImageEntity.toModel: UnsplashImage
    get() = UnsplashImage(
        url = this.imageUrl
    )