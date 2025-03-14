package uz.droid.wallatopia.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.jetbrains.compose.resources.painterResource
import uz.droid.wallatopia.LocalAnimatedVisibility
import uz.droid.wallatopia.LocalSharedTransition
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.domain.model.ImageUiModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AiGeneratedImageItem(
    modifier: Modifier = Modifier,
    image: ImageUiModel,
    isSelectionMode: Boolean = false,
    onClick: () -> Unit = {},
    onFavoriteClick: (Boolean) -> Unit = {},
    deleteOnClick: () -> Unit = {}
) {
    val isMoreJigglingOnRight = remember {
        (1..2).random() == 1
    }
    val randomExtra: Float = remember {
        (200..400).random().toFloat() / 1500f
    }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedDegree by infiniteTransition.animateFloat(
        initialValue = -BASE_ROTATION_DEGREE - if (isMoreJigglingOnRight) randomExtra / 2f else randomExtra,
        targetValue = BASE_ROTATION_DEGREE + if (isMoreJigglingOnRight) randomExtra else randomExtra / 2f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 150,
                easing = EaseInOutQuad
            ),
            repeatMode = RepeatMode.Reverse,
        ), label = "AnimatedDegree"
    )

    val transformOrigin = remember {
        transformOrigins.random()
    }
    val sharedTransitionScope = LocalSharedTransition.current!!
    val animatedVisibilityScope = LocalAnimatedVisibility.current!!
    with(sharedTransitionScope) {
        Box(
            modifier = Modifier
                .advancedShadow(
                    offsetY = 4.dp,
                    blur = 4.dp
                )
                .sharedElement(
                    state = rememberSharedContentState(image.thumbUrl),
                    animatedVisibilityScope = animatedVisibilityScope,
                    clipInOverlayDuringTransition = OverlayClip(AppTheme.shape.rounded7)
                )
                .clip(AppTheme.shape.rounded7)
                .height(170.dp)
                .graphicsLayer {
                    if (isSelectionMode) {
                        this.rotationZ = animatedDegree
                        this.transformOrigin = transformOrigin
                    }
                }
                .clickable(
                    onClick = onClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple()
                )
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(
                    LocalPlatformContext.current
                ).data(image.thumbUrl).crossfade(true).build(),
                contentScale = ContentScale.Crop
            )
            Box {
                Image(
                    modifier = modifier,
                    painter = painter,
                    contentDescription = image.thumbUrl,
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.BottomStart)
                        .bounceClickable {
                            onFavoriteClick(image.isFavorite)
                        },
                    contentAlignment = Alignment.BottomStart
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(start = 4.dp, bottom = 3.dp)
                            .clip(AppTheme.shape.circular)
                            .bounceClickable {
                                onFavoriteClick(image.isFavorite)
                            }
                            .background(
                                AppTheme.colorScheme.charcoalBlue.copy(0.53f),
                                shape = CircleShape
                            )
                            .padding(top = if (image.isFavorite) 1.dp else 0.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                if (image.isFavorite) Drawables.Icons.FavouriteSelected else Drawables.Icons.FavoriteOutlined
                            ),
                            contentDescription = "favorite",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
            if (isSelectionMode) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = AppTheme.colorScheme.immutableDark.copy(alpha = .5f)),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(
                        isSelectionMode,
                        modifier = Modifier,
                        enter = scaleIn(),
                        exit = scaleOut()
                    ) {
                        Icon(
                            painter = painterResource(Drawables.Icons.TrashIcon),
                            contentDescription = "Delete Icon",
                            tint = AppTheme.colorScheme.immutableWhite,
                            modifier = Modifier
                                .size(36.dp)
                                .clickable {
                                    deleteOnClick()
                                }
                        )
                    }

                }
            }


        }
    }
}

private val transformOrigins = listOf(
    TransformOrigin(0.2f, 0.05f),
    TransformOrigin(0.5f, 0.10f),
    TransformOrigin(0.5f, 0.5f),
    TransformOrigin(0.3f, 0.7f),
    TransformOrigin(0.9f, 0.07f),
)

private const val BASE_ROTATION_DEGREE = .3f