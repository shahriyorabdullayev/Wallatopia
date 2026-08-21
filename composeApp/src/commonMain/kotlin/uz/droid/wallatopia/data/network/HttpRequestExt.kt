package uz.droid.wallatopia.data.network

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodeURLPathPart
import io.ktor.http.path
import io.ktor.http.takeFrom

const val PIXABAY_URL = "https://pixabay.com/"
const val POLLINATIONS_IMAGE_URL = "https://image.pollinations.ai/"
const val POLLINATIONS_URL = "https://text.pollinations.ai/"

fun HttpRequestBuilder.json() {
    contentType(ContentType.Application.Json)
}

/**
 * [pathSegments] and [parameters] are encoded by Ktor, so user input is safe to pass in.
 */
fun HttpRequestBuilder.pixabayApiUrl(
    vararg pathSegments: String,
    parameters: Map<String, String> = emptyMap(),
) {
    url {
        takeFrom(PIXABAY_URL)
        path(*pathSegments)
        parameters.forEach { (key, value) -> this.parameters.append(key, value) }
    }
}

fun HttpRequestBuilder.pollinationsApiUrl(
    vararg pathSegments: String,
    parameters: Map<String, String> = emptyMap(),
) {
    url {
        takeFrom(POLLINATIONS_URL)
        path(*pathSegments)
        parameters.forEach { (key, value) -> this.parameters.append(key, value) }
    }
}

fun pollinationsImageUrl(
    prompt: String,
    model: String,
    width: Int,
    height: Int,
): String = buildString {
    append(POLLINATIONS_IMAGE_URL)
    append("prompt/")
    append(prompt.encodeURLPathPart())
    append("?model=${model.encodeURLPathPart()}")
    append("&width=$width&height=$height&safe=true&nologo=true")
}
