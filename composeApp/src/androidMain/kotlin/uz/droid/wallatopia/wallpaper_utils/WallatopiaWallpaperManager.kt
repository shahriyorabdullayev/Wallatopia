package uz.droid.wallatopia.wallpaper_utils

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmapOrNull
import coil3.annotation.ExperimentalCoilApi
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware

class WallatopiaWallpaperManager(
    private val wallpaperManager: WallpaperManager,
    private val context: Context
) {

    suspend fun setOnHomeScreen(imageUrl: String) {
        val bitmap = imageUrl.convertUrlToBitmap()
        bitmap?.let {
            wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM)
        }
    }

    suspend fun setOnLockScreen(imageUrl: String) {
        val bitmap = imageUrl.convertUrlToBitmap()
        bitmap?.let {
            wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK)
        }
    }

    suspend fun setOnBothScreens(imageUrl: String) {
        val bitmap = imageUrl.convertUrlToBitmap()
        bitmap?.let {
            wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM)
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    private suspend fun String.convertUrlToBitmap(): Bitmap? {
        val request = ImageRequest.Builder(context)
            .data(this)
            .allowHardware(false) // Disable hardware bitmaps.
            .build()

        val bitmap =
            (context.imageLoader.execute(request) as SuccessResult).image.asDrawable(context.resources)
                .toBitmapOrNull()
        return bitmap
    }
}