package uz.droid.wallatopia.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.droid.wallatopia.data.entities.WallatopiaImageEntity

@Dao
interface WallatopiaImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteImage(image: WallatopiaImageEntity)

    @Delete
    suspend fun deleteFavoriteImage(image: WallatopiaImageEntity)

    @Update
    suspend fun updateFavoriteImage(image: WallatopiaImageEntity)

    @Query("DELETE FROM wallatopia_images")
    suspend fun deleteAllFavorites()

    @Query("SELECT * FROM wallatopia_images WHERE is_favorite = true")
    fun getAllFavoriteImages(): Flow<List<WallatopiaImageEntity>>

    @Query("SELECT * FROM wallatopia_images WHERE is_ai_generated = true ORDER BY timestamp DESC")
    fun getAllAiGeneratedImagesByOrdered(): Flow<List<WallatopiaImageEntity>>

    @Query("SELECT EXISTS(SELECT * FROM wallatopia_images WHERE id = :id AND is_favorite = true)")
    suspend fun isFavorite(id: String): Boolean

}
