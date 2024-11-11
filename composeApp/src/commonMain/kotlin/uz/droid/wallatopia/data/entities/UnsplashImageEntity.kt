package uz.droid.wallatopia.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val UNSPLASH_IMAGES_TABLE = "unsplash_images"

@Entity(tableName = UNSPLASH_IMAGES_TABLE)
data class UnsplashImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
)