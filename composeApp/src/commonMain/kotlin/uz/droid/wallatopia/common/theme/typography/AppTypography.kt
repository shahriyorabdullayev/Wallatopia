package uz.droid.wallatopia.common.theme.typography

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

@Stable
data class AppTypography(
    val headlineNormal: TextStyle,
    val headlineSmall: TextStyle,
    val bodySmall: TextStyle,
    val titleLarge: TextStyle,
    val titleNormal: TextStyle,
    val titleSmall: TextStyle,
    val paragraph: TextStyle,
    val labelLarge: TextStyle,
    val labelNormal: TextStyle,
    val labelSmall: TextStyle
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        headlineNormal = TextStyle.Default,
        headlineSmall = TextStyle.Default,
        bodySmall = TextStyle.Default,
        titleLarge = TextStyle.Default,
        titleNormal = TextStyle.Default,
        titleSmall = TextStyle.Default,
        paragraph = TextStyle.Default,
        labelLarge = TextStyle.Default,
        labelNormal = TextStyle.Default,
        labelSmall = TextStyle.Default
    )
}