package uz.droid.wallatopia.data.mapper

import uz.droid.wallatopia.data.network.response.CategoryPhotoResponse
import uz.droid.wallatopia.data.network.response.CategoryResponse
import uz.droid.wallatopia.domain.model.CategoryUiModel
import uz.droid.wallatopia.domain.model.ImageUiModel

val CategoryResponse.toHomeCategoryUiModel: CategoryUiModel
    get() = CategoryUiModel(
        id = this.id,
        name = this.title,
        coverPhotoUrl = this.coverPhoto.urls.regular,
        blurHash = this.coverPhoto.blurHash,
        color = this.coverPhoto.color
    )

val CategoryPhotoResponse.toUiModel: ImageUiModel
    get() = ImageUiModel(
        id = this.id,
        url = this.urls.small,
        height = this.height,
        width = this.width,
        color = this.color,
        blurHash = this.blurHash
    )