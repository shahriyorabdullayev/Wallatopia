package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme

@Composable
fun MainImageItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit = {}
) {
    Box(
        Modifier.then(modifier)
            .clip(AppTheme.shape.rounded7)
            .clickable(onClick = onClick)
            .height(170.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl,
            contentDescription = imageUrl,
            contentScale = ContentScale.Crop
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 4.dp, bottom = 3.dp)
                .clip(AppTheme.shape.circular)
                .clickable(onClick = onFavoriteClick)
                .background(AppTheme.colorScheme.charcoalBlue.copy(0.53f))
                .padding(6.dp)
        ) {
            Image(
                painter = painterResource(Drawables.Icons.FavoriteOutlined),
                contentDescription = imageUrl
            )
        }
    }
}