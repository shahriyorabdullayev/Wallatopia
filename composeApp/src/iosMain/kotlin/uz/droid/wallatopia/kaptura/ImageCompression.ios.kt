package uz.droid.wallatopia.kaptura

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import platform.Foundation.NSData
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation

actual class ImageCompression {
    actual suspend fun compressImage(
        bytes: ByteArray,
        maxSize: Int
    ): ByteArray {
        return withContext(Dispatchers.Default) {
            var quality = 0.9
            val nsData = bytes.toNSData()
            var compressed: NSData?
            val image = UIImage(nsData)
            ensureActive()
            do {
                compressed = UIImageJPEGRepresentation(image, quality)
                quality -= 0.1
            } while (isActive &&
                compressed != null &&
                quality > 0.1 &&
                compressed.length > maxSize.toULong()
            )
            compressed.toByteArray() ?: bytes
        }
    }
}