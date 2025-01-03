package uz.droid.wallatopia.data.network.service

import uz.droid.wallatopia.data.network.response.Categories
import uz.droid.wallatopia.data.network.response.Photos
import uz.droid.wallatopia.data.network.response.SearchResponse

interface MainApiService {
    suspend fun fetchWallpapers(): Result<Photos>

    suspend fun fetchCategories(): Result<Categories>

    suspend fun fetchCategoryPhotos(categoryId: String): Result<Photos>

    suspend fun searchPhotos(query:String) :Result<SearchResponse>
}