package uz.droid.wallatopia.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val WALLATOPIA_IMAGES_TABLE = "wallatopia_images"

@Entity(tableName = WALLATOPIA_IMAGES_TABLE)
data class WallatopiaImageEntity(
    @ColumnInfo(name = "id")
    val id:String,
    @ColumnInfo("timestamp")
    val timestamp: Long,
    @ColumnInfo(name = "thumb_url")
    val thumbUrl: String,
    @PrimaryKey
    @ColumnInfo(name = "original_url")
    val originalUrl: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean,
    @ColumnInfo(name = "is_ai_generated")
    val isAiGenerated: Boolean,
)
