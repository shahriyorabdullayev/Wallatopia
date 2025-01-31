package uz.droid.wallatopia.data.repository

import uz.droid.wallatopia.data.network.response.Categories
import uz.droid.wallatopia.data.network.response.PixabayResponse
import uz.droid.wallatopia.data.network.response.SearchResponse
import uz.droid.wallatopia.data.network.response.SuggestionResponse
import uz.droid.wallatopia.data.network.service.MainApiService
import uz.droid.wallatopia.domain.repository.MainRepository

class MainRepositoryImpl(
    private val apiService: MainApiService,
) : MainRepository {

    override suspend fun fetchWallpapersFromPixabay(page:Int): Result<PixabayResponse> {
        return apiService.fetchWallpapersFromPixabay(page)
    }

    override suspend fun fetchCategories(): Result<Categories> {
        return apiService.fetchCategories()
    }

    override suspend fun searchPhotos(query: String, page: Int): Result<SearchResponse> {
        return apiService.searchPhotos(query,page)
    }

    override suspend fun getSuggestions(query: String): Result<SuggestionResponse> {
        return apiService.getSuggestions(query)
    }
}