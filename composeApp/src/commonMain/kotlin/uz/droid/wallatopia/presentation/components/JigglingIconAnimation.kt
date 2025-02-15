package uz.droid.wallatopia.presentation.components

import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


object DataProviderUtil {
    val colors = listOf(
        Color(0xFFE57373),  // Soft red
        Color(0xFF81C784),  // Mint green
        Color(0xFF64B5F6),  // Sky blue
        Color(0xFFFFB74D),  // Light orange
        Color(0xFFBA68C8),  // Light purple
        Color(0xFF4FC3F7),  // Bright blue
        Color(0xFFFFF176),  // Light yellow
        Color(0xFFFF8A65),  // Coral
        Color(0xFF9575CD),  // Medium purple
        Color(0xFF4DB6AC),  // Teal
        Color(0xFFFF80AB),  // Pink
        Color(0xFF7986CB),  // Indigo
        Color(0xFFA1887F),  // Brown
        Color(0xFF90A4AE),  // Blue grey
        Color(0xFFAED581),  // Light green
        Color(0xFFDCE775),  // Lime
        Color(0xFF4DD0E1),  // Cyan
        Color(0xFFF06292),  // Dark pink
        Color(0xFF7E57C2),  // Deep purple
        Color(0xFFFFB300),  // Amber
        Color(0xFF26A69A),  // Dark teal
        Color(0xFFFF7043),  // Deep orange
        Color(0xFF5C6BC0),  // Deep indigo
        Color(0xFF66BB6A),  // Dark green
        Color(0xFFEC407A),  // Pink variant
        Color(0xFF29B6F6),  // Light blue
        Color(0xFFAB47BC),  // Purple variant
        Color(0xFFFFCA28),  // Yellow variant
        Color(0xFF26C6DA),  // Cyan variant
        Color(0xFF42A5F5)   // Blue variant
    )

//    val roundedIcons = listOf(
//        Icons.Rounded.Games,
//        Icons.Rounded.PhotoCamera,
//        Icons.Rounded.MusicNote,
//        Icons.Rounded.Favorite,
//        Icons.Rounded.Restaurant,
//        Icons.Rounded.ShoppingCart,
//        Icons.Rounded.Palette,
//        Icons.Rounded.MovieFilter,
//        Icons.Rounded.FitnessCenter,
//        Icons.Rounded.LocalCafe,
//        Icons.Rounded.Explore,
//        Icons.Rounded.Book,
//        Icons.Rounded.Build,
//        Icons.Rounded.Calculate,
//        Icons.Rounded.Timer,
//        Icons.Rounded.LiveTv,
//        Icons.Rounded.Park,
//        Icons.Rounded.LocalMall,
//        Icons.Rounded.Brush,
//        Icons.Rounded.Dashboard,
//        Icons.Rounded.Psychology
//    )
}

// you can add more transform-origins to make the animation even more random
private val transformOrigins = listOf(
    TransformOrigin(0.2f, 0.05f),
    TransformOrigin(0.5f, 0.10f),
    TransformOrigin(0.5f, 0.5f),
    TransformOrigin(0.3f, 0.7f),
    TransformOrigin(0.9f, 0.07f),
)

private const val BASE_ROTATION_DEGREE = .3f


@Composable
fun JigglingIconAnimation(
    icon: ImageVector,
    inSelectionMode: Boolean,
    modifier: Modifier = Modifier,
) {
    val isMoreJigglingOnRight = remember {
        (1..2).random() == 1
    }
    val randomExtra: Float = remember {
        (200..400).random().toFloat() / 400f
    }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedDegree by infiniteTransition.animateFloat(
        initialValue = -BASE_ROTATION_DEGREE - if (isMoreJigglingOnRight) randomExtra / 2f else randomExtra,
        targetValue = BASE_ROTATION_DEGREE + if (isMoreJigglingOnRight) randomExtra else randomExtra / 2f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 120,
                easing = EaseInOutQuad
            ),
            repeatMode = RepeatMode.Reverse,
        ), label = "AnimatedDegree"
    )

    val transformOrigin = remember {
        transformOrigins.random()
    }
    val colors = remember { DataProviderUtil.colors.random() }

    Column(
        Modifier.then(
            if (inSelectionMode) Modifier.graphicsLayer {
                this.rotationZ = animatedDegree
                this.transformOrigin = transformOrigin
            } else Modifier
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .background(colors, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                inSelectionMode,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = (-3).dp, (-3).dp),
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Jiggling Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .background(Color.Gray, CircleShape)
                        .padding(2.dp)
                        .size(12.dp)
                )
            }
            Icon(
                imageVector = icon,
                contentDescription = "Jiggling Icon",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            )
        }

        Text("Dope", fontSize = 12.sp)
    }
}


@Composable
fun JiggleIconGridSample(
    modifier: Modifier = Modifier,
) {
    var isInSelectionMode by remember {
        mutableStateOf(false)
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
    ) {
        repeat(21) {
            item {
                JigglingIconAnimation(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    if (isInSelectionMode) {
                                        isInSelectionMode = false
                                    }
                                },
                                onLongPress = {
                                    isInSelectionMode = !isInSelectionMode
                                }
                            )
                        },
                    inSelectionMode = isInSelectionMode,
                    icon = Icons.Default.Add
                )
            }
        }
    }
}
