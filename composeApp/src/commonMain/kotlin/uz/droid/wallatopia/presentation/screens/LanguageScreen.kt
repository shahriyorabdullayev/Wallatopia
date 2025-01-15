package uz.droid.wallatopia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import uz.droid.wallatopia.Language
import uz.droid.wallatopia.LocalizedApp
import uz.droid.wallatopia.changeLang
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.common.utils.AppLanguage
import uz.droid.wallatopia.getAppCurrentLanguage
import uz.droid.wallatopia.presentation.components.CategoryTopBar
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.app_language

@Composable
fun LanguageScreen(
    onBackPressed: ()-> Unit = {}
) {
    val systemBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)
    var currentLanguage by remember { mutableStateOf(getAppCurrentLanguage()) }
    LocalizedApp(language = currentLanguage) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.eerieBlack)
                .padding(top = systemBarsPadding.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBarSection(
                onBackPressed = onBackPressed
            )
            LanguageSection(
                currentLanguage = currentLanguage,
                changeLanguage = {
                    currentLanguage = it
                    changeLang(currentLanguage)
                }
            )
        }
    }
}

@Composable
private fun LanguageSection(
    currentLanguage: String,
    changeLanguage: (languageCode: String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        LanguageItem(
            modifier = Modifier,
            language = Language.English,
            currentLanguage = currentLanguage,
            onClick = changeLanguage
        )
        LanguageItem(
            modifier = Modifier,
            language = Language.Uzbek,
            currentLanguage = currentLanguage,
            onClick = changeLanguage
        )
        LanguageItem(
            modifier = Modifier,
            language = Language.Russian,
            currentLanguage = currentLanguage,
            onClick = changeLanguage
        )
    }
}

@Composable
private fun LanguageItem(
    modifier: Modifier,
    language: Language,
    currentLanguage: String,
    onClick: (languageCode: String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .clickable(onClick = { onClick(language.isoFormat) })
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .padding(horizontal = 16.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = language.displayName,
                style = AppTheme.typography.buttonTextPrimary,
                color = AppTheme.colorScheme.immutableWhite,
                modifier = Modifier.weight(1f)
            )
            RadioButton(
                selected = language.isoFormat == currentLanguage,
                onClick = {
                    onClick(language.isoFormat)
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                colors = RadioButtonDefaults.colors(
                    selectedColor = AppTheme.colorScheme.green00AD9F,
                    unselectedColor = AppTheme.colorScheme.dimGray
                )
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            color = AppTheme.colorScheme.onyxGray
        )
    }
}

@Composable
private fun TopBarSection(
    onBackPressed: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Back),
            contentDescription = "Back icon",
            tint = AppTheme.colorScheme.immutableWhite,
            modifier = Modifier
                .clickable(onClick = onBackPressed)
                .padding(start = 29.dp, top = 8.dp)
        )
        Text(
            text = stringResource(Res.string.app_language),
            style = AppTheme.typography.pageHeadline,
            color = AppTheme.colorScheme.immutableWhite,
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
                .padding(top = 8.dp)
                .padding(vertical = 10.dp)
        )
    }

}
