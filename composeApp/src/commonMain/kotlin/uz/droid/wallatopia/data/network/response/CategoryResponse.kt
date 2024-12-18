package uz.droid.wallatopia.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val id: String,
    val title: String,
    @SerialName("cover_photo")
    val coverPhoto: CoverPhoto
)

@Serializable
data class CoverPhoto (
    val id: String,
    val width: Long,
    val height: Long,
    val color: String,

    @SerialName("blur_hash")
    val blurHash: String,

    val urls: Urls,
//    val links: CoverPhotoLinks,
//
//    @SerialName("preview_photos")
//    val previewPhotos: List<PreviewPhoto>
)

@Serializable
data class CoverPhotoLinks (
    val self: String,
    val html: String,
    val download: String,

    @SerialName("download_location")
    val downloadLocation: String
)

@Serializable
data class PreviewPhoto (
    val id: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    val urls: Urls
)