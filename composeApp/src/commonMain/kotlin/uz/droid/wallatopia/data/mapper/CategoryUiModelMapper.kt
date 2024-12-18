package uz.droid.wallatopia.data.mapper

import uz.droid.wallatopia.data.network.response.CategoryResponse
import uz.droid.wallatopia.domain.model.HomeCategoryModel

val CategoryResponse.toHomeCategoryUiModel: HomeCategoryModel
    get() = HomeCategoryModel(
        name = this.title,
        coverPhotoUrl = this.coverPhoto.urls.regular,
        blurHash = this.coverPhoto.blurHash
    )