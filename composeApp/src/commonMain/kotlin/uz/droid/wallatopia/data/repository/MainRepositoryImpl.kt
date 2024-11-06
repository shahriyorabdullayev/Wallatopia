package uz.droid.wallatopia.data.repository

import uz.droid.wallatopia.data.network.response.UnsplashResponse
import uz.droid.wallatopia.data.network.service.MainApiService
import uz.droid.wallatopia.domain.repository.MainRepository

class MainRepositoryImpl(private val apiService: MainApiService) : MainRepository {
    override suspend fun fetchWallpapers(): Result<List<UnsplashResponse>> {
        return apiService.fetchWallpapers()
    }
}