package uz.droid.wallatopia

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.droid.wallatopia.domain.model.ShareImageModel
import java.io.File

const val RATE_ANDROID_URL = "https://play.google.com/store/apps/details?id=uz.droid.wallatopia&hl=en"

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ShareManager(private val context: Context) {
    actual suspend fun shareImage(image: ShareImageModel): Result<Unit> {
        return kotlin.runCatching {
            withContext(Dispatchers.IO) {
                val savedFile = saveFile(image.fileName, image.bytes)
                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    savedFile
                )
                withContext(Dispatchers.Main) {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_STREAM, uri)
                        flags += Intent.FLAG_ACTIVITY_NEW_TASK
                        flags += Intent.FLAG_GRANT_READ_URI_PERMISSION
                        type = "image/*"
                    }
                    val chooser = Intent.createChooser(intent,null)
                    context.startActivity(chooser)
                }
            }
        }
    }

    private fun saveFile(fileName: String, bytes: ByteArray): File {
        val cache = context.cacheDir
        val savedFile = File(cache, fileName)
        savedFile.writeBytes(bytes)
        return savedFile
    }

    actual fun openRateUs() {
        val i = Intent(Intent.ACTION_VIEW)
        i.setData(Uri.parse(RATE_ANDROID_URL))
        context.startActivity(i)
    }
}

@Composable
actual fun rememberShareManager(): ShareManager {
    val context = LocalContext.current
    return remember { ShareManager(context) }
}