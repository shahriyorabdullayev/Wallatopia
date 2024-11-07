package uz.droid.wallatopia.common.theme.shapes

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

@Stable
data class AppShape(
    val circular: Shape,
)

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        circular = CircleShape
    )
}