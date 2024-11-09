package uz.droid.wallatopia.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.droid.wallatopia.data.local.FavoriteImagesDao
import uz.droid.wallatopia.data.mapper.toEntity
import uz.droid.wallatopia.data.mapper.toModel
import uz.droid.wallatopia.domain.model.UnsplashImage
import uz.droid.wallatopia.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(
    private val favoriteImagesDao: FavoriteImagesDao
): FavoritesRepository {
    override suspend fun insertUnsplashImage(image: UnsplashImage) {
        favoriteImagesDao.insertFavoriteImage(image.toEntity)
    }

    override suspend fun deleteUnsplashImage(image: UnsplashImage) {
        favoriteImagesDao.deleteFavoriteImage(image.toEntity)
    }

    override fun fetchFavoriteImages(): Flow<List<UnsplashImage>> {
        return favoriteImagesDao.getAllFavoriteImages().map { entities ->
            entities.map { it.toModel }
        }
    }

    override suspend fun clearFavorites() {
        favoriteImagesDao.deleteAllFavorites()
    }
}