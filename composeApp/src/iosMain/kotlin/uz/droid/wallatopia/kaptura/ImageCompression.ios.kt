package uz.droid.wallatopia.kaptura

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation

actual class ImageCompression {
    actual suspend fun compressImage(
        bytes: ByteArray,
        quality: Int
    ): ByteArray {
        return withContext(Dispatchers.Default) {
            val image = UIImage(bytes.toNSData())
            ensureActive()
            UIImageJPEGRepresentation(
                image,
                quality.coerceIn(1, 100) / 100.0
            ).toByteArray() ?: bytes
        }
    }
}
