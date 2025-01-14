package uz.droid.wallatopia.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestionResponse(
    @SerialName("suggestions")
    val suggestions: List<String>
)
