package uz.droid.wallatopia.wallpaper_utils

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class WallatopiaWallpaperManager(
    private val wallpaperManager: WallpaperManager
) {

    fun setOnHomeScreen(imageByteArray: ByteArray) {
        val bitmap = imageByteArray.convertToBitmap()
            wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM)
    }

    fun setOnLockScreen(imageByteArray: ByteArray) {
        val bitmap = imageByteArray.convertToBitmap()
            wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK)
    }

    fun setOnBothScreens(imageByteArray: ByteArray) {
        val bitmap = imageByteArray.convertToBitmap()
            wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM)
    }

    private fun ByteArray.convertToBitmap(): Bitmap {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }
}