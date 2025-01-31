package uz.droid.wallatopia.data.network

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json

const val PIXABAY_URL = "https://pixabay.com/"
const val POLLINATIONS_IMAGE_URL = "https://image.pollinations.ai/"
const val POLLINATIONS_URL = "https://text.pollinations.ai/"
const val PIXELS_API_KEY = "YfnXeuPMNatC1GgNcaJH0knqve0K7qZRZ9BOyHo09IzZq13X7fPRv0Vv"
const val PIXELS_URL = "https://api.pexels.com/"

fun HttpRequestBuilder.json() {
    contentType(ContentType.Application.Json)
    Json {
        coerceInputValues = true
    }
}


fun HttpRequestBuilder.pixabayApiUrl(path: String) {
    url {
        takeFrom(PIXABAY_URL)
        encodedPath = path
    }
}


fun HttpRequestBuilder.pollinationsApiUrl(path: String) {
    url {
        takeFrom(POLLINATIONS_URL)
        encodedPath = path
    }
}