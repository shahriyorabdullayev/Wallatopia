package uz.droid.wallatopia.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.droid.wallatopia.domain.model.UnsplashImage

interface FavoritesRepository {

    suspend fun insertUnsplashImage(image: UnsplashImage)

    suspend fun deleteUnsplashImage(image: UnsplashImage)

    suspend fun clearFavorites()

    fun fetchFavoriteImages(): Flow<List<UnsplashImage>>
}