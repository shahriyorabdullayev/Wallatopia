package uz.droid.wallatopia.common.theme.shapes

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

@Stable
data class AppShape(
    val circular: Shape,
    val rounded15: Shape,
    val rounded10: Shape,
    val rounded7: Shape,
    val rounded6: Shape,
    val rounded4: Shape,
    val rounded5: Shape,
)

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        circular = CircleShape,
        rounded15 = RectangleShape,
        rounded10 = RectangleShape,
        rounded7 = RectangleShape,
        rounded6 = RectangleShape,
        rounded4 = RectangleShape,
        rounded5 = RectangleShape,
    )
}