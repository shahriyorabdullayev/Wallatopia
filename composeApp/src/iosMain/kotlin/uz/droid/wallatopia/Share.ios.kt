package uz.droid.wallatopia

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import platform.Foundation.NSData
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSURL
import platform.Foundation.dataWithBytes
import platform.Foundation.writeToFile
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIImage
import platform.UIKit.UIImageWriteToSavedPhotosAlbum
import uz.droid.wallatopia.domain.model.ShareImageModel

const val RATE_IOS_URL = ""

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ShareManager {
    actual suspend fun shareImage(image: ShareImageModel): Result<Unit> {
        return kotlin.runCatching {
            val url = withContext(Dispatchers.IO) {
                saveFile(image.bytes, image.fileName)
            }
            val activityViewController = UIActivityViewController(listOf(url), null)
            UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
                activityViewController, animated = true, completion = null
            )
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun saveFile(bytes: ByteArray, fileName: String): NSURL? {
        val tempDir = NSTemporaryDirectory()
        val sharedFile = tempDir + fileName
        val saved = bytes.usePinned {
            val nsData = NSData.dataWithBytes(it.addressOf(0), bytes.size.toULong())
            nsData.writeToFile(sharedFile, true)
        }

        return if (saved) NSURL.fileURLWithPath(sharedFile) else null
    }

    actual fun openRateUs() {
        val nsURL = NSURL.URLWithString(RATE_IOS_URL)
        nsURL?.let {
            UIApplication.sharedApplication.openURL(it)
        }
    }
}

@Composable
actual fun rememberShareManager(): ShareManager {
    return remember { ShareManager() }
}

@OptIn(ExperimentalForeignApi::class)
suspend fun saveImageToGallery(image: ByteArray, onSuccess: () -> Unit, onFailure: () -> Unit) {
    withContext(Dispatchers.IO) {
        image.usePinned {
            val nsData = NSData.dataWithBytes(it.addressOf(0), image.size.toULong())
            val uiImage = UIImage.imageWithData(nsData)
            if (uiImage != null) {
                UIImageWriteToSavedPhotosAlbum(uiImage, null, null, null)
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } else {
                withContext(Dispatchers.Main) {
                    onFailure()
                }
            }
        }
    }
}
