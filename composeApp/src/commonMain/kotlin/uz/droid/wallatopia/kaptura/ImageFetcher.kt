package uz.droid.wallatopia.kaptura

import uz.droid.wallatopia.kaptura.lru.LruCache


typealias ImageLRU = LruCache<String, ByteArray>

interface ImageFetcher {
    suspend fun fetch(
        url: String,
    ): ByteArray
}