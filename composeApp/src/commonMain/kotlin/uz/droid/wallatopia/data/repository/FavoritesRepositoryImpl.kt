package uz.droid.wallatopia.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.droid.wallatopia.data.local.WallatopiaImagesDao
import uz.droid.wallatopia.data.mapper.toEntity
import uz.droid.wallatopia.data.mapper.toUiModel
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val wallatopiaImagesDao: WallatopiaImagesDao
): FavoritesRepository {
    override suspend fun insertImage(image: ImageUiModel) {
        wallatopiaImagesDao.insertFavoriteImage(image.toEntity)
    }

    override suspend fun deleteImage(image: ImageUiModel) {
        wallatopiaImagesDao.deleteFavoriteImage(image.toEntity)
    }

    override suspend fun updateImage(image: ImageUiModel) {
        wallatopiaImagesDao.updateFavoriteImage(image.toEntity)
    }

    override fun fetchFavoriteImages(): Flow<List<ImageUiModel>> {
        return wallatopiaImagesDao.getAllFavoriteImages().map { entities ->
            entities.map { it.toUiModel }
        }
    }

    override fun fetchAiGeneratedImages(): Flow<List<ImageUiModel>> {
        return wallatopiaImagesDao.getAllAiGeneratedImagesByOrdered().map { entities ->
            entities.map { it.toUiModel }
        }
    }

    override suspend fun clearFavorites() {
        wallatopiaImagesDao.deleteAllFavorites()
    }

    override  fun isFavorite(id: String): Flow<Boolean> {
        return wallatopiaImagesDao.isFavorite(id)
    }
}