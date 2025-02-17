package uz.droid.wallatopia.domain.model

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import com.eygraber.uri.UriCodec
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class ImageUiModel(
    val id: String,
    val thumbUrl: String,
    val originalUrl: String,
    val color: String = "",
    val isFavorite: Boolean = false,
    val isAiGenerated: Boolean = false,
    val timestamp: Long
)

val ImageUiModelNavType = object : NavType<ImageUiModel>(
    isNullableAllowed = false,
) {
    override fun get(bundle: Bundle, key: String): ImageUiModel? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): ImageUiModel {
        return Json.decodeFromString(UriCodec.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: ImageUiModel) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun serializeAsValue(value: ImageUiModel): String {
        return UriCodec.encode(Json.encodeToString(value))
    }
}