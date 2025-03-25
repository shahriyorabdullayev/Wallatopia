package uz.droid.wallatopia.wallpaper_utils

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WallatopiaWallpaperManager(
    private val wallpaperManager: WallpaperManager
) {

    suspend fun setOnHomeScreen(imageByteArray: ByteArray, onSuccess: () -> Unit) {
        withContext(Dispatchers.IO) {
            val bitmap = imageByteArray.convertToBitmap()
            wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM)
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    suspend fun setOnLockScreen(imageByteArray: ByteArray, onSuccess: () -> Unit) {
        withContext(Dispatchers.IO) {
            val bitmap = imageByteArray.convertToBitmap()
            wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK)
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    suspend fun setOnBothScreens(imageByteArray: ByteArray, onSuccess: () -> Unit) {
        withContext(Dispatchers.IO) {
            val bitmap = imageByteArray.convertToBitmap()
            wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM)
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    private fun ByteArray.convertToBitmap(): Bitmap {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }
}