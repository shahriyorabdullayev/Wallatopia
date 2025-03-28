package uz.droid.wallatopia.data.network.service.impl

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import uz.droid.wallatopia.common.secrets.AppSecrets
import uz.droid.wallatopia.data.local.STATIC_CATEGORIES
import uz.droid.wallatopia.data.network.api.handle
import uz.droid.wallatopia.data.network.json
import uz.droid.wallatopia.data.network.pixabayApiUrl
import uz.droid.wallatopia.data.network.pollinationsApiUrl
import uz.droid.wallatopia.data.network.response.Categories
import uz.droid.wallatopia.data.network.response.PixabayResponse
import uz.droid.wallatopia.data.network.response.SearchResponse
import uz.droid.wallatopia.data.network.response.SuggestionResponse
import uz.droid.wallatopia.data.network.service.MainApiService

val PIXABAY_KEY = AppSecrets.pixabayApiKey

class MainApiServiceImpl(private val httpClient: HttpClient) : MainApiService {

    override suspend fun fetchWallpapersFromPixabay(page: Int): Result<PixabayResponse> {
        return httpClient.handle {
            this.get {
                json()
                pixabayApiUrl("api/?key=$PIXABAY_KEY&image_type=all&pretty=true&page=$page&safesearch=true")
            }
        }
    }

    override suspend fun fetchCategories(): Result<Categories> {
        return Result.success(STATIC_CATEGORIES.shuffled())
    }

    override suspend fun searchPhotos(query: String, page: Int): Result<SearchResponse> {
        return httpClient.handle {
            this.get {
                json()
                pixabayApiUrl("api/?key=$PIXABAY_KEY&image_type=all&pretty=true&q=$query&page=$page")
            }
        }
    }

    override suspend fun getSuggestions(query: String): Result<SuggestionResponse> {
        return httpClient.handle {
            this.get {
                json()
                pollinationsApiUrl("5commonsuggestionwordsandphrasesstartingwith${query}andresponselistnamenamedsuggestions?json=true")
            }
        }
    }
}