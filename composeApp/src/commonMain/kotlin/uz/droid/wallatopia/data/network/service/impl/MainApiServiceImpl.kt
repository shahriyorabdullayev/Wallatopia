package uz.droid.wallatopia.data.network.service.impl

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import uz.droid.wallatopia.data.network.api.handle
import uz.droid.wallatopia.data.network.apiUrl
import uz.droid.wallatopia.data.network.json
import uz.droid.wallatopia.data.network.response.CategoryResponse
import uz.droid.wallatopia.data.network.response.Photos
import uz.droid.wallatopia.data.network.service.MainApiService

val token = "kUsXzb97PY9dJuPL-kzDZQ0R0g8d4c759S6wrhu4RnU"

class MainApiServiceImpl(private val httpClient: HttpClient) : MainApiService {

    override suspend fun fetchWallpapers(): Result<Photos> {
        return httpClient.handle {
            this.get {
                json()
                apiUrl("photos?page=1&per_page=20")
            }
        }
    }

    override suspend fun fetchCategories(): Result<List<CategoryResponse>> {
        return httpClient.handle {
            this.get {
                json()
                apiUrl("topics?page=1&per_page=15")
            }
        }
    }
}