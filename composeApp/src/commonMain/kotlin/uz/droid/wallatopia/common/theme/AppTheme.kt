package uz.droid.wallatopia.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.droid.wallatopia.common.theme.colors.AppColorScheme
import uz.droid.wallatopia.common.theme.colors.LocalAppColorScheme
import uz.droid.wallatopia.common.theme.colors.ashGray
import uz.droid.wallatopia.common.theme.colors.charcoalBlue
import uz.droid.wallatopia.common.theme.colors.charlestonGray
import uz.droid.wallatopia.common.theme.colors.chineseSilverGray
import uz.droid.wallatopia.common.theme.colors.cyanBlue
import uz.droid.wallatopia.common.theme.colors.dimGray
import uz.droid.wallatopia.common.theme.colors.eerieBlack
import uz.droid.wallatopia.common.theme.colors.gainsBoroGray
import uz.droid.wallatopia.common.theme.colors.green00AD9F
import uz.droid.wallatopia.common.theme.colors.green00F798
import uz.droid.wallatopia.common.theme.colors.immutableDark
import uz.droid.wallatopia.common.theme.colors.immutableWhite
import uz.droid.wallatopia.common.theme.colors.jetGray
import uz.droid.wallatopia.common.theme.colors.malachiteGreen
import uz.droid.wallatopia.common.theme.colors.neonFuchsiaPink
import uz.droid.wallatopia.common.theme.colors.onyxGray
import uz.droid.wallatopia.common.theme.colors.persianGreen
import uz.droid.wallatopia.common.theme.colors.platinumGray
import uz.droid.wallatopia.common.theme.colors.purple4C00AD
import uz.droid.wallatopia.common.theme.colors.purpleA800F7
import uz.droid.wallatopia.common.theme.colors.quickSilverGray
import uz.droid.wallatopia.common.theme.colors.silverGray
import uz.droid.wallatopia.common.theme.colors.softWhite
import uz.droid.wallatopia.common.theme.colors.sonicSilverGray
import uz.droid.wallatopia.common.theme.colors.spanishGray
import uz.droid.wallatopia.common.theme.colors.violet6B37FF
import uz.droid.wallatopia.common.theme.colors.vividOrange
import uz.droid.wallatopia.common.theme.shapes.AppShape
import uz.droid.wallatopia.common.theme.shapes.LocalAppShape
import uz.droid.wallatopia.common.theme.typography.AppTypography
import uz.droid.wallatopia.common.theme.typography.LocalAppTypography
import uz.droid.wallatopia.common.theme.typography.SoraFont

val darkColorScheme = AppColorScheme(
    immutableWhite = immutableWhite,
    immutableDark = immutableDark,
    purpleA800F7 = purpleA800F7,
    purple4C00AD = purple4C00AD,
    green00F798 = green00F798,
    violet6B37FF = violet6B37FF,
    green00AD9F = green00AD9F,
    eerieBlack = eerieBlack,
    spanishGray = spanishGray,
    gainsBoroGray = gainsBoroGray,
    charcoalBlue = charcoalBlue,
    dimGray = dimGray,
    persianGreen = persianGreen,
    malachiteGreen = malachiteGreen,
    charlestonGray = charlestonGray,
    quickSilverGray = quickSilverGray,
    jetGray = jetGray,
    sonicSilverGray = sonicSilverGray,
    ashGray = ashGray,
    vividOrange = vividOrange,
    chineseSilverGray = chineseSilverGray,
    platinumGray = platinumGray,
    neonFuchsiaPink = neonFuchsiaPink,
    onyxGray = onyxGray,
    silverGray = silverGray,
    softWhite = softWhite,
    cyanBlue = cyanBlue
)

val lightColorScheme = AppColorScheme(
    immutableWhite = immutableWhite,
    immutableDark = immutableDark,
    purpleA800F7 = purpleA800F7,
    purple4C00AD = purple4C00AD,
    green00F798 = green00F798,
    violet6B37FF = violet6B37FF,
    green00AD9F = green00AD9F,
    eerieBlack = eerieBlack,
    spanishGray = spanishGray,
    gainsBoroGray = gainsBoroGray,
    charcoalBlue = charcoalBlue,
    dimGray = dimGray,
    persianGreen = persianGreen,
    malachiteGreen = malachiteGreen,
    charlestonGray = charlestonGray,
    quickSilverGray = quickSilverGray,
    jetGray = jetGray,
    sonicSilverGray = sonicSilverGray,
    ashGray = ashGray,
    vividOrange = vividOrange,
    chineseSilverGray = chineseSilverGray,
    platinumGray = platinumGray,
    neonFuchsiaPink = neonFuchsiaPink,
    onyxGray = onyxGray,
    silverGray = silverGray,
    softWhite = softWhite,
    cyanBlue = cyanBlue
)

@Composable
private fun typography() = AppTypography(
    appNameTitle = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp
    ),
    splashTitle = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Thin,
        fontSize = 24.sp
    ),
    pageHeadline = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),

    sectionHeadline = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight(646),
        fontSize = (17.5).sp
    ),

    bodyText = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),

    inputText = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    hintText = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),

    buttonTextSmall = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Bold,
        fontSize = 7.sp
    ),

    buttonTextMedium = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Medium,
        fontSize = (9.44).sp
    ),

    buttonTextLarge = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),

    buttonTextPrimary = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),

    buttonTextSecondary = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),

    listTitle = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),

    listItemTitle = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp
    ),

    caption = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    ),

    labelSmall = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    ),

    labelMedium = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp
    ),

    labelLarge = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Light,
        fontSize = 24.sp
    ),
    settingsItemTitle = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    sheetItemTitle = TextStyle(
        fontFamily = SoraFont(),
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    )
)

private val shape = AppShape(
    circular = CircleShape,
    rounded15 = RoundedCornerShape(15.dp),
    rounded7 = RoundedCornerShape(7.dp),
    rounded10 = RoundedCornerShape(10.dp),
    rounded6 = RoundedCornerShape(6.dp),
    rounded4 = RoundedCornerShape(4.dp),
    rounded5 = RoundedCornerShape(5.dp),
)

@Stable
@Composable
fun WallatopiaAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorScheme
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides typography(),
        LocalAppShape provides shape,
        content = content
    )
}

object AppTheme {
    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current
}