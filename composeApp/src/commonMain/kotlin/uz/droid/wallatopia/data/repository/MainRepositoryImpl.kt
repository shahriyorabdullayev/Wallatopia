package uz.droid.wallatopia.data.repository

import kotlinx.coroutines.flow.Flow
import uz.droid.wallatopia.data.network.response.Categories
import uz.droid.wallatopia.data.network.response.Photos
import uz.droid.wallatopia.data.network.response.PixabayResponse
import uz.droid.wallatopia.data.network.response.SearchResponse
import uz.droid.wallatopia.data.network.response.SuggestionResponse
import uz.droid.wallatopia.data.network.service.MainApiService
import uz.droid.wallatopia.domain.repository.MainRepository

class MainRepositoryImpl(
    private val apiService: MainApiService
) : MainRepository {
    override suspend fun fetchWallpapers(): Result<Photos> {
        return apiService.fetchWallpapers()
    }

    override suspend fun fetchWallpapersFromPixabay(): Result<PixabayResponse> {
        return apiService.fetchWallpapersFromPixabay()
    }

    override suspend fun fetchCategories(): Result<Categories> {
        return apiService.fetchCategories()
    }

    override suspend fun fetchCategoryPhotos(categoryId:String): Result<Photos> {
        return apiService.fetchCategoryPhotos(categoryId = categoryId)
    }

    override suspend fun searchPhotos(query: String): Result<SearchResponse> {
        return apiService.searchPhotos(query)
    }

    override suspend fun getSuggestions(query: String): Result<SuggestionResponse> {
        return apiService.getSuggestions(query)
    }
}