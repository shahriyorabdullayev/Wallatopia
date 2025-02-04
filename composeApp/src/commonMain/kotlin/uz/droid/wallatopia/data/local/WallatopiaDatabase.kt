package uz.droid.wallatopia.data.local

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import uz.droid.wallatopia.common.Constants
import uz.droid.wallatopia.data.entities.WallatopiaImageEntity

@Database(
    entities = [WallatopiaImageEntity::class],
    version = Constants.DATABASE_VERSION,
)
@ConstructedBy(WallatopiaConstructor::class)
abstract class WallatopiaDatabase : RoomDatabase() {
    abstract fun favoriteImagesDao(): WallatopiaImagesDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object WallatopiaConstructor : RoomDatabaseConstructor<WallatopiaDatabase> {
    override fun initialize(): WallatopiaDatabase
}


