package uz.droid.wallatopia.kaptura

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

interface Kapture {
    suspend fun load(url: String): ImageResult
}

class KaptureImpl(
    private val imageFetcher: ImageFetcher,
    private val lru: ImageLRU = ImageLRU(20),
    private val imageIo: ImageIO,
) : Kapture {

    override suspend fun load(url: String): ImageResult {
        return withContext(Dispatchers.IO) {
            val cached = lru.get(url)
            if (cached != null) {
                ImageResult.Done(ImageData(cached, LoadSource.Cache))
            } else {
                val local = imageIo.load(url)
                if (local == null) {
                    loadRemote(url)
                } else {
                    lru.save(url, local)
                    ImageResult.Done(ImageData(local, LoadSource.Local))
                }
            }
        }
    }


    private suspend fun loadRemote(url: String): ImageResult {
        return try {
            val bytes = imageFetcher.fetch(url)
            lru.save(url, bytes)
            supervisorScope {
                launch {
                    imageIo.save(bytes, url)
                }.join()
            }
            ImageResult.Done(ImageData(bytes, LoadSource.Remote))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            ImageResult.Error(e)
        }
    }
}