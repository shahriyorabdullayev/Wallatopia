package uz.droid.wallatopia.data.repository

import uz.droid.wallatopia.data.network.response.Categories
import uz.droid.wallatopia.data.network.response.Photos
import uz.droid.wallatopia.data.network.response.SearchResponse
import uz.droid.wallatopia.data.network.service.MainApiService
import uz.droid.wallatopia.domain.repository.MainRepository

class MainRepositoryImpl(
    private val apiService: MainApiService
) : MainRepository {
    override suspend fun fetchWallpapers(): Result<Photos> {
        return apiService.fetchWallpapers()
    }

    override suspend fun fetchCategories(): Result<Categories> {
        return apiService.fetchCategories()
    }

    override suspend fun fetchCategoryPhotos(categoryId:String): Result<Photos> {
        return apiService.fetchCategoryPhotos(categoryId = categoryId)
    }

    override suspend fun searchPhotosPhotos(query: String): Result<SearchResponse> {
        return apiService.searchPhotos(query)
    }
}