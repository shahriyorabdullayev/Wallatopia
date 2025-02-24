package uz.droid.wallatopia.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.presentation.viewmodels.SettingsViewModel
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.app_language
import wallatopia.composeapp.generated.resources.app_logo
import wallatopia.composeapp.generated.resources.privacy_title
import wallatopia.composeapp.generated.resources.rate_us
import wallatopia.composeapp.generated.resources.settings_title
import wallatopia.composeapp.generated.resources.terms_title

@Composable
fun SettingsScreen(
    navigateToLanguage: () -> Unit,
    navigateToTerms: () -> Unit,
    navigateToPrivacy: () -> Unit
) {
    val viewModel: SettingsViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val event = viewModel::onEventDispatch
    val systemBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.eerieBlack)
            .padding(top = systemBarsPadding.calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarSection()
        AppLogoSection()
        SettingsSection(
            languageOnClick = navigateToLanguage,
            termsOnClick = navigateToTerms,
            privacyOnClick = navigateToPrivacy,
            rateUsOnClick = {

            },
        )
    }
}

@Composable
private fun SettingsSection(
    languageOnClick: () -> Unit = {},
    rateUsOnClick: () -> Unit = {},
    termsOnClick: () -> Unit = {},
    privacyOnClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SettingsItem(
            modifier = Modifier
                .clickable(onClick = languageOnClick)
                .padding(horizontal = 16.dp),
            settingName = stringResource(Res.string.app_language)
        )
        SettingsItem(
            modifier = Modifier
                .clickable(onClick = termsOnClick)
                .padding(horizontal = 16.dp),
            settingName = stringResource(Res.string.terms_title)
        )
        SettingsItem(
            modifier = Modifier
                .clickable(onClick = privacyOnClick)
                .padding(horizontal = 16.dp),
            settingName = stringResource(Res.string.privacy_title)
        )
        SettingsItem(
            modifier = Modifier
                .clickable(onClick = rateUsOnClick)
                .padding(horizontal = 16.dp)
                .alpha(0.7f),
            settingName = stringResource(Res.string.rate_us)
        )
    }
}

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    settingName: String,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = settingName,
            style = AppTheme.typography.settingsItemTitle,
            color = AppTheme.colorScheme.immutableWhite,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(start = 24.dp)
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            color = AppTheme.colorScheme.onyxGray
        )
    }

}

@Composable
private fun TopBarSection() {
    Text(
        text = stringResource(Res.string.settings_title),
        style = AppTheme.typography.pageHeadline,
        color = AppTheme.colorScheme.immutableWhite,
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(vertical = 10.dp)
    )
}

@Composable
private fun AppLogoSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp)
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(Res.drawable.app_logo),
            contentDescription = "App logo",
            modifier = Modifier
                .size(100.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Wallatopia",
            style = AppTheme.typography.pageHeadline.copy(fontSize = 18.sp),
            color = AppTheme.colorScheme.immutableWhite,
        )
    }
}