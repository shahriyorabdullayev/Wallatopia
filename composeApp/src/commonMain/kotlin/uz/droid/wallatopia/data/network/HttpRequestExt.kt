package uz.droid.wallatopia.data.network

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import uz.droid.wallatopia.data.network.service.impl.token

const val UNSPLASH_URL = "https://api.unsplash.com/"

fun HttpRequestBuilder.json() {
    contentType(ContentType.Application.Json)
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