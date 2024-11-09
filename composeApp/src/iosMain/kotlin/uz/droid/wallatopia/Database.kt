package uz.droid.wallatopia

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask
import uz.droid.wallatopia.common.Constants
import uz.droid.wallatopia.data.local.WallatopiaDatabase

fun getMyDatabase(): RoomDatabase.Builder<WallatopiaDatabase> {
    val dbFile = documentDirectory() + "/${Constants.DATABASE_NAME}"
    return Room.databaseBuilder<WallatopiaDatabase>(
        name = dbFile,
    )
}

@OptIn(ExperimentalForeignApi::class)
fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}