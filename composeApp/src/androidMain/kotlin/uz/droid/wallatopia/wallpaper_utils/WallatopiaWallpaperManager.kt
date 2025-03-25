package uz.droid.wallatopia.wallpaper_utils

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class WallatopiaWallpaperManager(
    private val wallpaperManager: WallpaperManager
) {

    fun setOnHomeScreen(imageByteArray: ByteArray, onSuccess: () -> Unit) {
        val bitmap = imageByteArray.convertToBitmap()
        wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM)
        onSuccess()
    }

    fun setOnLockScreen(imageByteArray: ByteArray, onSuccess: () -> Unit) {
        val bitmap = imageByteArray.convertToBitmap()
        wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK)
        onSuccess()
    }

    fun setOnBothScreens(imageByteArray: ByteArray, onSuccess: () -> Unit) {
        val bitmap = imageByteArray.convertToBitmap()
        wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM)
        onSuccess()
    }

    private fun ByteArray.convertToBitmap(): Bitmap {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }
}