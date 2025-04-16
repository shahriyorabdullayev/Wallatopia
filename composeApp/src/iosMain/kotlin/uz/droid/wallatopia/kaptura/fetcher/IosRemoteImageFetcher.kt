package uz.droid.wallatopia.kaptura.fetcher

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import platform.Foundation.NSHTTPURLResponse
import platform.Foundation.NSMutableURLRequest
import platform.Foundation.NSURL
import platform.Foundation.NSURLSession
import platform.Foundation.dataTaskWithRequest
import uz.droid.wallatopia.kaptura.ImageCompression
import uz.droid.wallatopia.kaptura.ImageFetcher
import uz.droid.wallatopia.kaptura.toByteArray
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class IosRemoteImageFetcher(
    private val compressor: ImageCompression
) : ImageFetcher {
    override suspend fun fetch(url: String): ByteArray {
        return withContext(Dispatchers.IO) {
            val bytes = suspendCancellableCoroutine<ByteArray> { continuation ->
                val nsUrl = NSURL.URLWithString(url) ?: run {
                    continuation.resumeWithException(Throwable("URL badly formatted"))
                    return@suspendCancellableCoroutine
                }
                val nsRequest = NSMutableURLRequest.requestWithURL(nsUrl).apply {
                    setAllowsCellularAccess(true)
                    setTimeoutInterval(10.toDouble())
                }
                val session = NSURLSession.sharedSession

                val task = session.dataTaskWithRequest(
                    request = nsRequest,
                    completionHandler = { data, response, error ->
                        when {
                            error != null -> {
                                continuation.resumeWithException(Exception(error.localizedDescription))
                            }

                            data != null -> {
                                val bytes = data.toByteArray()
                                bytes?.let {
                                    continuation.resume(it)
                                } ?: run {
                                    continuation.resumeWithException(Exception("Decoding image failed"))
                                }
                            }

                            else -> {
                                val statusCode = (response as NSHTTPURLResponse).statusCode
                                continuation.resumeWithException(Exception("Could not fetch image $statusCode"))
                            }
                        }

                    },
                )
                task.resume()
            }
            compressor.compressImage(bytes, maxSize = 80)
        }
    }
}