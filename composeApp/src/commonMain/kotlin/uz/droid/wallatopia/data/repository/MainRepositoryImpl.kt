package uz.droid.wallatopia.data.repository

import uz.droid.wallatopia.data.network.response.CategoryPhotoResponse
import uz.droid.wallatopia.data.network.response.CategoryResponse
import uz.droid.wallatopia.data.network.response.UnsplashResponse
import uz.droid.wallatopia.data.network.service.MainApiService
import uz.droid.wallatopia.domain.repository.MainRepository

class MainRepositoryImpl(
    private val apiService: MainApiService
) : MainRepository {
    override suspend fun fetchWallpapers(): Result<List<UnsplashResponse>> {
        return apiService.fetchWallpapers()
    }

    override suspend fun fetchCategories(): Result<List<CategoryResponse>> {
        return apiService.fetchCategories()
    }

    override suspend fun fetchCategoryPhotos(categoryId:String): Result<List<CategoryPhotoResponse>> {
        return apiService.fetchCategoryPhotos(categoryId = categoryId)
    }
}