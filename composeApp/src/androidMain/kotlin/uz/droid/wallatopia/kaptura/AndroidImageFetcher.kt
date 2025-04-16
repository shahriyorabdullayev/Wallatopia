package uz.droid.wallatopia.kaptura

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class AndroidImageFetcher(
    private val compressor: ImageCompression,
) : ImageFetcher {
    override suspend fun fetch(
        url: String,
    ): ByteArray {
        return withContext(Dispatchers.IO) {
            val bytes = suspendCancellableCoroutine { continuation ->
                try {
                    val connection = URL(url).openConnection() as HttpURLConnection
                    connection.apply {
                        connectTimeout = 20_000
                        readTimeout = 20_000
                        doOutput
                        connect()
                    }
                    val code = connection.responseCode
                    if (code == HttpURLConnection.HTTP_OK) {
                        val inputStream = BufferedInputStream(connection.inputStream)
                        val output = ByteArrayOutputStream()
                        output.use { stream ->
                            inputStream.copyTo(stream)
                        }
                        continuation.resume(output.toByteArray())

                    } else {
                        continuation.resumeWithException(Throwable("Error Fetching Image ${code}"))
                    }
                    connection.disconnect()
                } catch (e: Exception) {
                    if (e is CancellationException) throw e
                    e.printStackTrace()
                    continuation.resumeWithException(e)
                }
            }
            compressor.compressImage(bytes, 80)
        }
    }
}