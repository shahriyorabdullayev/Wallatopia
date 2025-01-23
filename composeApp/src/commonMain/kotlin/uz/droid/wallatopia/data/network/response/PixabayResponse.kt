package uz.droid.wallatopia.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PixabayResponse(
    @SerialName("hits") val hits: List<PixabayImage>
)

@Serializable
data class PixabayImage(
    @SerialName ("id") val id: Long,
    @SerialName ("webformatURL") val largeImageURL: String
)
