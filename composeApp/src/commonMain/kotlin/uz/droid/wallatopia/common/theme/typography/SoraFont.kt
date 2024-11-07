package uz.droid.wallatopia.common.theme.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.sora_bold
import wallatopia.composeapp.generated.resources.sora_extra_bold
import wallatopia.composeapp.generated.resources.sora_light
import wallatopia.composeapp.generated.resources.sora_medium
import wallatopia.composeapp.generated.resources.sora_regular
import wallatopia.composeapp.generated.resources.sora_semi_bold

@Composable
fun SoraFont() = FontFamily(
    Font(Res.font.sora_light, weight = FontWeight.Light),
    Font(Res.font.sora_regular, weight = FontWeight.Normal),
    Font(Res.font.sora_medium, weight = FontWeight.Medium),
    Font(Res.font.sora_semi_bold, weight = FontWeight.SemiBold),
    Font(Res.font.sora_bold, weight = FontWeight.Bold),
    Font(Res.font.sora_extra_bold, weight = FontWeight.ExtraBold),
)