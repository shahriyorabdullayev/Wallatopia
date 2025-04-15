package uz.droid.wallatopia.kaptura

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException

class AndroidImageIo(
    private val context: Context,
    private val urlEncoder: UrlEncoder,
) : ImageIO {
    override suspend fun save(
        bytes: ByteArray,
        url: String
    ) {
        withContext(Dispatchers.IO) {
            val name = urlEncoder.encode(url)
            val root = createAppDirectory()
            val file = File(root, name)
            if (file.parentFile?.exists() == false) {
                file.parentFile?.mkdirs()
            }
            file.writeBytes(bytes)
        }
    }

    override suspend fun load(url: String): ByteArray? {
        return try {
            val encoded = urlEncoder.encode(url)
            val root = createAppDirectory()
            val file = File(root, encoded)
            if (!file.exists()) {
                null
            } else {
                file.readBytes()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    }


    private fun createAppDirectory(): File {
        val dir = File(context.cacheDir, DIR_NAME)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

}