package uz.droid.wallatopia.kaptura

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun rememberKapture(): Kapture {
    val context = LocalContext.current
    val fetcher = remember {
        AndroidImageFetcher(ImageCompression())
    }
    val imageIo = remember {
        AndroidImageIo(context, UrlEncoder())
    }
    return remember {
        KaptureImpl(fetcher, imageIo = imageIo)
    }
}