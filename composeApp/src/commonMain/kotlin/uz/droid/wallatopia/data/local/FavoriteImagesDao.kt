package uz.droid.wallatopia.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.droid.wallatopia.data.entities.UnsplashImageEntity

@Dao
interface FavoriteImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteImage(image: UnsplashImageEntity)

    @Delete
    suspend fun deleteFavoriteImage(image: UnsplashImageEntity)

    @Query("DELETE FROM unsplash_images")
    suspend fun deleteAllFavorites()

    @Query("SELECT * FROM unsplash_images")
    fun getAllFavoriteImages(): Flow<List<UnsplashImageEntity>>
}
