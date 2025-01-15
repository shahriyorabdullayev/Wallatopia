package uz.droid.wallatopia

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalLocalization = staticCompositionLocalOf { Language.Uzbek.isoFormat }

@Composable
fun LocalizedApp(language: String, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalLocalization provides language,
        content = content
    )
}