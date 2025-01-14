package uz.droid.wallatopia.data.network

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
import uz.droid.wallatopia.data.network.service.impl.token

const val UNSPLASH_URL = "https://api.unsplash.com/"
const val POLLINATIONS_URL = "https://text.pollinations.ai/"

fun HttpRequestBuilder.json() {
    contentType(ContentType.Application.Json)
    Json {
        coerceInputValues = true
    }
}

fun HttpRequestBuilder.apiUrl(path: String) {
    url {
        takeFrom(UNSPLASH_URL)
        encodedPath = path
        if (token.isNotBlank()) {
            header("Authorization", "Client-ID $token)}")
        }
    }
}

fun HttpRequestBuilder.pollinationsApiUrl(path: String) {
    url {
        takeFrom(POLLINATIONS_URL)
        encodedPath = path
    }
}