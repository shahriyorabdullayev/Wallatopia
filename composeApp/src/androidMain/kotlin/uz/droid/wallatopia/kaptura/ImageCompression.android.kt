package uz.droid.wallatopia.kaptura

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

actual class ImageCompression {
    actual suspend fun compressImage(
        bytes: ByteArray,
        maxSize: Int
    ): ByteArray {
        return withContext(Dispatchers.Default) {
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            val format = Bitmap.CompressFormat.JPEG
            ensureActive()
            var cmopressed: ByteArray
            var quality = 90
            do {
                ByteArrayOutputStream().use { output ->
                    bitmap.compress(format, quality, output)
                    cmopressed = output.toByteArray()
                    quality -= 10
                }
            } while (
                cmopressed.size > maxSize &&
                isActive &&
                quality > 10
            )
            cmopressed
        }
    }
}