package uz.droid.wallatopia.data.mapper

import uz.droid.wallatopia.data.network.response.CategoryResponse
import uz.droid.wallatopia.domain.model.CategoryUiModel

val CategoryResponse.toHomeCategoryUiModel: CategoryUiModel
    get() = CategoryUiModel(
        id = this.id,
        name = this.title,
        coverPhotoUrl = this.coverPhoto.urls.regular,
        blurHash = this.coverPhoto.blurHash,
        color = this.coverPhoto.color
    )