package uz.droid.wallatopia.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryPhotoResponse(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String,

    @SerialName("blur_hash")
    val blurHash: String?,
    val likes: Long?,
    val urls: Urls,

    @SerialName("liked_by_user")
    val likedByUser: Boolean?,
    val description: String?,
    val user: User?
)