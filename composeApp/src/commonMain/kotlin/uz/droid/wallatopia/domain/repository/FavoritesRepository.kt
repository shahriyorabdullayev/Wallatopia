package uz.droid.wallatopia.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.droid.wallatopia.domain.model.ImageUiModel

interface FavoritesRepository {

    suspend fun insertImage(image: ImageUiModel)

    suspend fun deleteImage(image: ImageUiModel)

    suspend fun updateImage(image: ImageUiModel)

    suspend fun clearFavorites()

    fun fetchFavoriteImages(): Flow<List<ImageUiModel>>

    fun fetchAiGeneratedImages(): Flow<List<ImageUiModel>>

    fun isFavorite(id: String): Flow<Boolean>
}