package uz.droid.wallatopia.kaptura

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import uz.droid.wallatopia.kaptura.fetcher.IosRemoteImageFetcher

@Composable
actual fun rememberKapture(): Kapture {
    val fetcher = remember {
        IosRemoteImageFetcher(ImageCompression())
    }

    val imageIo = remember {
        IosImageIO(UrlEncoder())
    }
    return remember {
        KaptureImpl(fetcher, imageIo = imageIo)
    }
}