package uz.droid.wallatopia.common.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColorScheme(
    val immutableWhite: Color, //FFFFFF
    val immutableDark: Color, //000000
    val purpleA800F7: Color, //A800F7
    val purple4C00AD: Color, //4C00AD
    val green00F798: Color, //00F798
    val violet6B37FF: Color, //6B37FF
    val green00AD9F: Color, //00AD9F
    val eerieBlack: Color,//1a1a1a
    val spanishGray: Color, //989898
    val gainsBoroGray: Color, //d9d9d9
    val charcoalBlue: Color, //191e31
    val dimGray: Color, //545454
    val persianGreen: Color, //00AD9F
    val malachiteGreen: Color, //00F798
    val charlestonGray: Color, //262626
    val quickSilverGray: Color, //a1a1a1
    val jetGray: Color, //343434
    val sonicSilverGray: Color, //707070
    val ashGray: Color, //7E7E7E
    val vividOrange: Color, //FF7A00
    val chineseSilverGray: Color,//CBCBCB
    val platinumGray: Color, //E2E2E2
    val neonFuchsiaPink: Color, //FF7DE2
    val onyxGray: Color, //404040
    val silverGray: Color, //A7A7A7,
    val softWhite:Color //F6F6F6
)

val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        immutableWhite = Color.Unspecified,
        immutableDark = Color.Unspecified,
        purpleA800F7 = Color.Unspecified,
        purple4C00AD = Color.Unspecified,
        green00F798 = Color.Unspecified,
        violet6B37FF = Color.Unspecified,
        green00AD9F = Color.Unspecified,
        eerieBlack = Color.Unspecified,
        spanishGray = Color.Unspecified,
        gainsBoroGray = Color.Unspecified,
        charcoalBlue = Color.Unspecified,
        dimGray = Color.Unspecified,
        persianGreen = Color.Unspecified,
        malachiteGreen = Color.Unspecified,
        charlestonGray = Color.Unspecified,
        quickSilverGray = Color.Unspecified,
        jetGray = Color.Unspecified,
        sonicSilverGray = Color.Unspecified,
        ashGray = Color.Unspecified,
        vividOrange = Color.Unspecified,
        chineseSilverGray = Color.Unspecified,
        platinumGray = Color.Unspecified,
        neonFuchsiaPink = Color.Unspecified,
        onyxGray = Color.Unspecified,
        silverGray = Color.Unspecified,
        softWhite = Color.Unspecified
    )
}