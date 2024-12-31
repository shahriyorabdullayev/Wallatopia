package uz.droid.wallatopia.domain.repository

import uz.droid.wallatopia.data.network.response.Categories
import uz.droid.wallatopia.data.network.response.Photos
import uz.droid.wallatopia.data.network.response.SearchResponse

interface MainRepository {
    suspend fun fetchWallpapers(): Result<Photos>

    suspend fun fetchCategories(): Result<Categories>

    suspend fun fetchCategoryPhotos(categoryId:String): Result<Photos>

    suspend fun searchPhotosPhotos(query:String): Result<SearchResponse>
}