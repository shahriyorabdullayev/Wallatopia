package uz.droid.wallatopia.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.material.ripple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import uz.droid.wallatopia.common.utils.NoRippleInteractionSource

fun Modifier.bounceClickable(
    minScale: Float = 0.5f,
    onAnimationFinished: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) = composed {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) minScale else 1f,
        label = ""
    ) {
        if (isPressed) {
            isPressed = false
            onAnimationFinished?.invoke()
        }
    }

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = NoRippleInteractionSource(),
            indication = ripple()
        ) {
            isPressed = true
            onClick?.invoke()
        }
}