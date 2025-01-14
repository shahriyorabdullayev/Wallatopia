package uz.droid.wallatopia.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.droid.wallatopia.data.network.response.Categories
import uz.droid.wallatopia.data.network.response.Photos
import uz.droid.wallatopia.data.network.response.SearchResponse
import uz.droid.wallatopia.data.network.response.SuggestionResponse

interface MainRepository {
    suspend fun fetchWallpapers(): Result<Photos>

    suspend fun fetchCategories(): Result<Categories>

    suspend fun fetchCategoryPhotos(categoryId:String): Result<Photos>

    suspend fun searchPhotos(query:String): Result<SearchResponse>

    suspend fun getSuggestions(query:String): Result<SuggestionResponse>
}