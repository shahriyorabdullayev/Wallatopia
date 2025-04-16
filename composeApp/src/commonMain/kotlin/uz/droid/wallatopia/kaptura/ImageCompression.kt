package uz.droid.wallatopia.kaptura


expect class ImageCompression {
    suspend fun compressImage(
        bytes: ByteArray,
        maxSize: Int,
    ): ByteArray
}