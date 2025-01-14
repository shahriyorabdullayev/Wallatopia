package uz.droid.wallatopia.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.droid.wallatopia.domain.model.ImageUiModel

interface FavoritesRepository {

    suspend fun insertImage(image: ImageUiModel)

    suspend fun deleteImage(image: ImageUiModel)

    suspend fun clearFavorites()

    fun fetchFavoriteImages(): Flow<List<ImageUiModel>>

    suspend fun isFavorite(id: String): Boolean
}