package uz.droid.wallatopia.kaptura


const val DEFAULT_IMAGE_QUALITY = 85

expect class ImageCompression {
    /**
     * Re-encodes [bytes] as JPEG with the given [quality] (1..100).
     */
    suspend fun compressImage(
        bytes: ByteArray,
        quality: Int,
    ): ByteArray
}
