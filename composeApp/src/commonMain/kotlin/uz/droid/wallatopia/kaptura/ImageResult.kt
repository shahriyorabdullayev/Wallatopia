package uz.droid.wallatopia.kaptura


enum class LoadSource {

    Cache,
    Local,
    Remote,
}

class ImageData(
    val bytes: ByteArray,
    val source: LoadSource,
)

sealed interface ImageResult {
    data object Loading : ImageResult
    data class Error(val error: Throwable? = null) : ImageResult
    class Done(val imageData: ImageData) : ImageResult
}