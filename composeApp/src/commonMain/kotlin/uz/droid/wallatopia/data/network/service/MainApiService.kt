package uz.droid.wallatopia.data.network.service

import uz.droid.wallatopia.common.Constants.GENERATIVE_MODELS
import kotlinx.coroutines.flow.Flow
import uz.droid.wallatopia.data.network.response.Categories
import uz.droid.wallatopia.data.network.response.Photos
import uz.droid.wallatopia.data.network.response.SearchResponse
import uz.droid.wallatopia.data.network.response.SuggestionResponse

interface MainApiService {
    suspend fun fetchWallpapers(): Result<Photos>

    suspend fun fetchCategories(): Result<Categories>

    suspend fun fetchCategoryPhotos(categoryId: String): Result<Photos>

    suspend fun generateImage(
        prompt: String,
        model:String = GENERATIVE_MODELS[1],
    ): Result<Any>
    suspend fun searchPhotos(query: String): Result<SearchResponse>

    suspend fun getSuggestions(query: String): Result<SuggestionResponse>
}