package uz.droid.wallatopia.kaptura

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import org.jetbrains.compose.resources.stringResource
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.image_generating_error


@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    loading: @Composable () -> Unit,
    error: @Composable (String) -> Unit,
    success: () -> Unit,
    forPreview: (@Composable (Modifier) -> Unit)? = null,
    contentDescription: String? = null,
    url: String,
) {
    if (forPreview != null) {
        forPreview(modifier)
    } else {
        RuntimeAsyncImage(
            modifier = modifier,
            loading = loading,
            error = error,
            success = success,
            url = url,
            contentDescription = contentDescription
        )

    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RuntimeAsyncImage(
    modifier: Modifier = Modifier,
    loading: @Composable () -> Unit,
    error: @Composable (String) -> Unit,
    success: () -> Unit,
    url: String,
    contentDescription: String? = null,
) {
    val kapture = rememberKapture()
    var result by remember {
        mutableStateOf<ImageResult>(ImageResult.Loading)
    }


    LaunchedEffect(Unit) {
        result = kapture.load(url)
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (result) {
            is ImageResult.Done -> {
                Image(
                    bitmap = (result as ImageResult.Done).imageData.bytes.decodeToImageBitmap(),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                )
                success()
            }

            is ImageResult.Error -> error((result as ImageResult.Error).error?.message ?: stringResource(Res.string.image_generating_error))
            ImageResult.Loading -> loading()
        }
    }
}