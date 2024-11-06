package uz.droid.wallatopia.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

typealias Photos = List<UnsplashResponse>


@Serializable
data class UnsplashResponse (
    val id: String,
    val slug: String,

    @SerialName("alternative_slugs")
    val alternativeSlugs: AlternativeSlugs,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("promoted_at")
    val promotedAt: String? = null,

    val width: Long,
    val height: Long,
    val color: String,

    @SerialName("blur_hash")
    val blurHash: String,

    val description: String? = null,

    @SerialName("alt_description")
    val altDescription: String,

    val breadcrumbs: JsonArray,
    val urls: Urls,
    val links: WelcomeLinks,
    val likes: Long,

    @SerialName("liked_by_user")
    val likedByUser: Boolean,

    @SerialName("current_user_collections")
    val currentUserCollections: JsonArray,

    val sponsorship: Sponsorship? = null,

    @SerialName("topic_submissions")
    val topicSubmissions: TopicSubmissions,

    @SerialName("asset_type")
    val assetType: AssetType,

    val user: User
)

@Serializable
data class AlternativeSlugs (
    val en: String,
    val es: String,
    val ja: String,
    val fr: String,
    val it: String,
    val ko: String,
    val de: String,
    val pt: String
)

@Serializable
enum class AssetType(val value: String) {
    @SerialName("photo") Photo("photo");
}

@Serializable
data class WelcomeLinks (
    val self: String,
    val html: String,
    val download: String,

    @SerialName("download_location")
    val downloadLocation: String
)

@Serializable
data class Sponsorship (
    @SerialName("impression_urls")
    val impressionUrls: List<String>,

    val tagline: String,

    @SerialName("tagline_url")
    val taglineURL: String,

    val sponsor: User
)

@Serializable
data class User (
    val id: String,

    @SerialName("updated_at")
    val updatedAt: String,

    val username: String,
    val name: String,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String? = null,

    @SerialName("twitter_username")
    val twitterUsername: String? = null,

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
    val totalLikes: Long,

    @SerialName("total_photos")
    val totalPhotos: Long,

    @SerialName("total_promoted_photos")
    val totalPromotedPhotos: Long,

    @SerialName("total_illustrations")
    val totalIllustrations: Long,

    @SerialName("total_promoted_illustrations")
    val totalPromotedIllustrations: Long,

    @SerialName("accepted_tos")
    val acceptedTos: Boolean,

    @SerialName("for_hire")
    val forHire: Boolean,

    val social: Social
)

@Serializable
data class UserLinks (
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
)

@Serializable
data class ProfileImage (
    val small: String,
    val medium: String,
    val large: String
)

@Serializable
data class Social (
    @SerialName("instagram_username")
    val instagramUsername: String? = null,

    @SerialName("portfolio_url")
    val portfolioURL: String? = null,

    @SerialName("twitter_username")
    val twitterUsername: String? = null,

    @SerialName("paypal_email")
    val paypalEmail: JsonElement? = null
)

@Serializable
data class TopicSubmissions (
    val wallpapers: ArchitectureInterior? = null,

    @SerialName("3d-renders")
    val the3DRenders: The3_DRenders? = null,

    @SerialName("architecture-interior")
    val architectureInterior: ArchitectureInterior? = null,

    val nature: The3_DRenders? = null,
    val travel: The3_DRenders? = null,
    val spirituality: ArchitectureInterior? = null,
    val experimental: The3_DRenders? = null,

    @SerialName("textures-patterns")
    val texturesPatterns: The3_DRenders? = null
)

@Serializable
data class ArchitectureInterior (
    val status: String
)

@Serializable
data class The3_DRenders (
    val status: String,

    @SerialName("approved_on")
    val approvedOn: String? = null
)

@Serializable
data class Urls (
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,

    @SerialName("small_s3")
    val smallS3: String
)

