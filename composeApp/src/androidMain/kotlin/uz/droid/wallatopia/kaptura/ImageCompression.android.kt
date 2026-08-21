package uz.droid.wallatopia.kaptura

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

actual class ImageCompression {
    actual suspend fun compressImage(
        bytes: ByteArray,
        quality: Int
    ): ByteArray {
        return withContext(Dispatchers.Default) {
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                ?: return@withContext bytes
            ensureActive()
            try {
                ByteArrayOutputStream().use { output ->
                    bitmap.compress(
                        Bitmap.CompressFormat.JPEG,
                        quality.coerceIn(1, 100),
                        output
                    )
                    output.toByteArray()
                }
            } finally {
                bitmap.recycle()
            }
        }
    }
}
