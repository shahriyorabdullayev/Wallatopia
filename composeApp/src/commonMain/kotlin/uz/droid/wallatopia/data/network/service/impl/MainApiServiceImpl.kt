package uz.droid.wallatopia.data.network.service.impl

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import uz.droid.wallatopia.data.network.api.handle
import uz.droid.wallatopia.data.network.apiUrl
import uz.droid.wallatopia.data.network.json
import uz.droid.wallatopia.data.network.pixabayApiUrl
import uz.droid.wallatopia.data.network.pollinationsApiUrl
import uz.droid.wallatopia.data.network.response.Categories
import uz.droid.wallatopia.data.network.response.Photos
import uz.droid.wallatopia.data.network.response.PixabayResponse
import uz.droid.wallatopia.data.network.response.SearchResponse
import uz.droid.wallatopia.data.network.response.SuggestionResponse
import uz.droid.wallatopia.data.network.service.MainApiService

val token = "kUsXzb97PY9dJuPL-kzDZQ0R0g8d4c759S6wrhu4RnU"

class MainApiServiceImpl(private val httpClient: HttpClient) : MainApiService {

    override suspend fun fetchWallpapers(): Result<Photos> {
        return httpClient.handle {
            this.get {
                json()
                apiUrl("photos?page=2&per_page=20")
            }
        }
    }

    override suspend fun fetchWallpapersFromPixabay(): Result<PixabayResponse> {
        return httpClient.handle {
            this.get {
                json()
                pixabayApiUrl("api/?key=26668198-7ea1a3552ef573fa65c198a0e&image_type=all&pretty=true")
            }
        }
    }

    override suspend fun fetchCategories(): Result<Categories> {
        return httpClient.handle {
            this.get {
                json()
                apiUrl("topics?page=1&per_page=15")
            }
        }
    }

    override suspend fun fetchCategoryPhotos(categoryId: String): Result<Photos> {
        return httpClient.handle {
            this.get {
                json()
                apiUrl("topics/$categoryId/photos?page=1&per_page=15")
            }
        }
    }

    override suspend fun searchPhotos(query: String): Result<SearchResponse> {
        return httpClient.handle {
            this.get {
                json()
                apiUrl("search/photos?query=$query&page=1&per_page=15")
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