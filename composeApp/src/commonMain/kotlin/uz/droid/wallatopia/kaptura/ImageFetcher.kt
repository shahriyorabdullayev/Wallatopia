package uz.droid.wallatopia.kaptura

import uz.droid.wallatopia.kaptura.lru.LruCache


typealias ImageLRU = LruCache<String, ByteArray>

private const val MEMORY_CACHE_SIZE = 20L

/**
 * Shared across every [uz.droid.wallatopia.kaptura.KaptureImpl] instance: [rememberKapture]
 * builds a new Kapture per composable, so a per-instance cache would never be hit.
 */
val imageMemoryCache: ImageLRU by lazy { ImageLRU(MEMORY_CACHE_SIZE) }

interface ImageFetcher {
    suspend fun fetch(
        url: String,
    ): ByteArray
}