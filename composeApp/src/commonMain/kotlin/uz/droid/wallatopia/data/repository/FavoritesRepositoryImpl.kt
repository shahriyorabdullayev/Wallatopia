package uz.droid.wallatopia.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import uz.droid.wallatopia.data.local.WallatopiaImagesDao
import uz.droid.wallatopia.data.mapper.toEntity
import uz.droid.wallatopia.data.mapper.toUiModel
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val wallatopiaImagesDao: WallatopiaImagesDao
): FavoritesRepository {
    override suspend fun insertImage(image: ImageUiModel) {
        withContext(Dispatchers.IO) {
            wallatopiaImagesDao.insertFavoriteImage(image.toEntity)
        }
    }

    override suspend fun deleteImage(image: ImageUiModel) {
        withContext(Dispatchers.IO) {
            wallatopiaImagesDao.deleteFavoriteImage(image.toEntity)
        }
    }

    override suspend fun updateImage(image: ImageUiModel) {
        withContext(Dispatchers.IO) {
            wallatopiaImagesDao.updateFavoriteImage(image.toEntity)
        }
    }

    override fun fetchFavoriteImages(): Flow<List<ImageUiModel>> {
        return wallatopiaImagesDao.getAllFavoriteImages().map { entities ->
            entities.map { it.toUiModel }
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchAiGeneratedImages(): Flow<List<ImageUiModel>> {
        return wallatopiaImagesDao.getAllAiGeneratedImagesByOrdered().map { entities ->
            entities.map { it.toUiModel }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun clearFavorites() {
        withContext(Dispatchers.IO) {
            wallatopiaImagesDao.deleteAllFavorites()
        }
    }

    override  fun isFavorite(id: String): Flow<Boolean> {
        return wallatopiaImagesDao.isFavorite(id).flowOn(Dispatchers.IO)
    }
}