package uz.droid.wallatopia.data.network.service

import uz.droid.wallatopia.data.network.response.CategoryResponse
import uz.droid.wallatopia.data.network.response.UnsplashResponse

interface MainApiService {
    suspend fun fetchWallpapers(): Result<List<UnsplashResponse>>

    suspend fun fetchCategories(): Result<List<CategoryResponse>>
}