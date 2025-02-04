package uz.droid.wallatopia.data.mapper

import uz.droid.wallatopia.data.entities.WallatopiaImageEntity
import uz.droid.wallatopia.domain.model.ImageUiModel

val ImageUiModel.toEntity: WallatopiaImageEntity
    get() = WallatopiaImageEntity(
        id = this.id,
        thumbUrl = this.thumbUrl,
        originalUrl = this.originalUrl,
        isFavorite = this.isFavorite,
        isAiGenerated = this.isAiGenerated,
        timestamp = this.timestamp
    )

val WallatopiaImageEntity.toUiModel: ImageUiModel
    get() = ImageUiModel(
        id = this.id,
        thumbUrl = this.thumbUrl,
        originalUrl = this.originalUrl,
        isFavorite = this.isFavorite,
        isAiGenerated = this.isAiGenerated,
        timestamp = this.timestamp
    )