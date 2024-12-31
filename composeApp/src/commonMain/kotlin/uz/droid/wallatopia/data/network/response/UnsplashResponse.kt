package uz.droid.wallatopia.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

typealias Photos = List<UnsplashResponse>


@Serializable
data class UnsplashResponse(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String,

    @SerialName("blur_hash")
    val blurHash: String,

    val description: String? = null,
    val urls: Urls,
    val user: User
)

@Serializable
data class User(
    val id: String,

    val username: String,
    val name: String,

    @SerialName("portfolio_url")
    val portfolioURL: String? = null,

    val bio: String? = null,
    val location: String? = null,
    val links: UserLinks,

    @SerialName("profile_image")
    val profileImage: ProfileImage,

    @SerialName("instagram_username")
    val instagramUsername: String? = null,

    @SerialName("total_collections")
    val totalCollections: Long,

    @SerialName("total_likes")
    val totalLikes: Long
)

@Serializable
data class UserLinks(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
)

@Serializable
data class ProfileImage(
    val small: String,
    val medium: String,
    val large: String
)

@Serializable
data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

