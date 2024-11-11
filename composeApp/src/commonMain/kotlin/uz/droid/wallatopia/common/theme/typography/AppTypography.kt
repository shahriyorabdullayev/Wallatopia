package uz.droid.wallatopia.common.theme.typography

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

@Stable
data class AppTypography(
    val appNameTitle:TextStyle,
    val splashTitle:TextStyle,
    val pageHeadline: TextStyle,
    val sectionHeadline: TextStyle,
    val bodyText: TextStyle,
    val inputText: TextStyle,
    val hintText: TextStyle,
    val buttonTextSmall: TextStyle,
    val buttonTextMedium: TextStyle,
    val buttonTextLarge: TextStyle,
    val buttonTextPrimary: TextStyle,
    val buttonTextSecondary: TextStyle,
    val listTitle: TextStyle,
    val listItemTitle: TextStyle,
    val caption: TextStyle,
    val labelSmall: TextStyle,
    val labelMedium: TextStyle,
    val labelLarge: TextStyle,
    val settingsItemTitle:TextStyle,
    val sheetItemTitle:TextStyle,
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        appNameTitle = TextStyle.Default,
        splashTitle = TextStyle.Default,
        pageHeadline = TextStyle.Default,
        sectionHeadline = TextStyle.Default,
        bodyText = TextStyle.Default,
        inputText = TextStyle.Default,
        hintText = TextStyle.Default,
        buttonTextSmall = TextStyle.Default,
        buttonTextMedium = TextStyle.Default,
        buttonTextLarge = TextStyle.Default,
        buttonTextPrimary = TextStyle.Default,
        buttonTextSecondary = TextStyle.Default,
        listTitle = TextStyle.Default,
        listItemTitle = TextStyle.Default,
        caption = TextStyle.Default,
        labelSmall = TextStyle.Default,
        labelMedium = TextStyle.Default,
        labelLarge = TextStyle.Default,
        settingsItemTitle = TextStyle.Default,
        sheetItemTitle = TextStyle.Default
    )
}