package uz.droid.wallatopia

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.droid.wallatopia.common.Constants
import uz.droid.wallatopia.data.local.WallatopiaDatabase

fun getMyDatabase(context: Context): RoomDatabase.Builder<WallatopiaDatabase> {
    val dbFile = context.getDatabasePath(Constants.DATABASE_NAME)
    return Room.databaseBuilder<WallatopiaDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath,
    )
}
