package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.jetbrains.compose.resources.painterResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.domain.model.ImageUiModel

@Composable
fun MainImageItem(
    modifier: Modifier = Modifier,
    image: ImageUiModel,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit = {}
) {
    Box(
        Modifier.then(modifier)
            .advancedShadow(
                offsetY = 4.dp,
                blur = 4.dp
            )
            .clip(AppTheme.shape.rounded7)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            )
            .height(170.dp)
    ) {

        val placeHolderColor = "FF${image.color.takeLast(6)}".toLong(16)
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(
                LocalPlatformContext.current
            ).data(image.url).crossfade(true).build(),
            placeholder = ColorPainter(Color(placeHolderColor)),
            contentScale = ContentScale.Crop
        )

        Box {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = image.url,
                contentScale = ContentScale.Crop,
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 4.dp, bottom = 3.dp)
                .clip(AppTheme.shape.circular)
                .clickable(
                    onClick = onFavoriteClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple()
                )
                .background(AppTheme.colorScheme.charcoalBlue.copy(0.53f))
                .padding(start = 6.dp, top = 6.dp, bottom = 5.dp, end = 6.dp)
        ) {
            Image(
                painter = painterResource(Drawables.Icons.FavoriteOutlined),
                contentDescription = "favorite"
            )
        }
    }
}