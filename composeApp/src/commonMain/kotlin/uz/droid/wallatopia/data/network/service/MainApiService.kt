package uz.droid.wallatopia.data.network.service

import uz.droid.wallatopia.data.network.response.Categories
import uz.droid.wallatopia.data.network.response.PixabayResponse
import uz.droid.wallatopia.data.network.response.SearchResponse
import uz.droid.wallatopia.data.network.response.SuggestionResponse

interface MainApiService {

    suspend fun fetchWallpapersFromPixabay(page: Int): Result<PixabayResponse>

    suspend fun fetchCategories(): Result<Categories>

    suspend fun searchPhotos(query: String, page: Int): Result<SearchResponse>

    suspend fun getSuggestions(query: String): Result<SuggestionResponse>
}