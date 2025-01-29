package uz.droid.wallatopia.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.isAndroid
import uz.droid.wallatopia.presentation.components.advancedShadow
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.wallpapers

@Composable
fun ImageDetailsScreen(
    thumbUrl: String = "",
    originalUrl: String = "",
    onBackPressed: () -> Unit = {}
) {
    val statusBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)
    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues(LocalDensity.current)
    val screenSize = remember { mutableStateOf(IntSize(-1, -1)) }
    Layout(
        content = {
            val painterBlur = rememberAsyncImagePainter(
                model = ImageRequest.Builder(
                    LocalPlatformContext.current
                ).data(thumbUrl).crossfade(true).build(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterBlur,
                    contentDescription = "Wallpaper",
                    modifier = Modifier.fillMaxSize().blur(radius = 10.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = statusBarsPadding.calculateTopPadding()),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Drawables.Icons.Back),
                            contentDescription = "Back icon",
                            tint = AppTheme.colorScheme.immutableWhite,
                            modifier = Modifier
                                .padding(start = 29.dp, top = 8.dp)
                                .clickable(onClick = onBackPressed, interactionSource = null, indication = null)
                        )
                        Text(
                            text = stringResource(Res.string.wallpapers),
                            style = AppTheme.typography.pageHeadline,
                            color = AppTheme.colorScheme.immutableWhite,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 12.dp)
                                .padding(top = 8.dp)
                                .padding(vertical = 10.dp)
                        )
                    }
                    SubcomposeAsyncImage(
                        modifier = Modifier.weight(1f)
                            .padding(vertical = 24.dp)
                            .padding(horizontal = 40.dp)
                            .advancedShadow(
                                blur = 13.dp
                            )
                            .clip(AppTheme.shape.rounded15),
                        model = ImageRequest.Builder(
                            LocalPlatformContext.current
                        )
                            .data(originalUrl)
                            .crossfade(true).build(),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        loading = {

                        },
                        onSuccess = {

                        }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = navigationBarsPadding.calculateBottomPadding()+16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(Drawables.Icons.Share),
                                contentDescription = "Share icon",
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(color = AppTheme.colorScheme.charcoalBlue.copy(alpha = 0.53f), shape = CircleShape)
                                    .padding(10.dp),
                                tint = AppTheme.colorScheme.immutableWhite
                            )
                            Text(
                                text = "Share",
                                style = AppTheme.typography.buttonTextMedium.copy(fontSize = 12.sp),
                                color = AppTheme.colorScheme.immutableWhite,
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .background(color = AppTheme.colorScheme.charcoalBlue.copy(alpha = 0.53f), shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomEnd = 10.dp, bottomStart = 10.dp))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        if (isAndroid){
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(Drawables.Icons.SetWallpaper),
                                    contentDescription = "Share icon",
                                    modifier = Modifier
                                        .padding(horizontal = 24.dp)
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(color = AppTheme.colorScheme.immutableWhite, shape = CircleShape)
                                        .padding(14.dp),
                                    tint = AppTheme.colorScheme.immutableDark
                                )
                                Text(
                                    text = "Set",
                                    style = AppTheme.typography.buttonTextMedium.copy(fontSize = 12.sp),
                                    color = AppTheme.colorScheme.immutableWhite,
                                    modifier = Modifier
                                        .padding(top = 6.dp)
                                        .background(color = AppTheme.colorScheme.charcoalBlue.copy(alpha = 0.53f), shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomEnd = 10.dp, bottomStart = 10.dp))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        } else {
                            Spacer(modifier = Modifier.width(40.dp))
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(Drawables.Icons.FavoriteOutlined),
                                contentDescription = "Share icon",
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(color = AppTheme.colorScheme.charcoalBlue.copy(alpha = 0.53f), shape = CircleShape)
                                    .padding(10.dp),
                                tint = AppTheme.colorScheme.immutableWhite
                            )
                            Text(
                                text = "Favorite",
                                style = AppTheme.typography.buttonTextMedium.copy(fontSize = 12.sp),
                                color = AppTheme.colorScheme.immutableWhite,
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .background(color = AppTheme.colorScheme.charcoalBlue.copy(alpha = 0.53f), shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomEnd = 10.dp, bottomStart = 10.dp))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

            }
        },
        measurePolicy = { measurables, constraints ->
            // Use the max width and height from the constraints
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            screenSize.value = IntSize(width, height)
            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

            layout(width, height) {
                var yPosition = 0
                placeables.forEach { placeable ->
                    placeable.placeRelative(x = 0, y = yPosition)
                    yPosition += placeable.height
                }
            }
        }
    )


}