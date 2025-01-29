package uz.droid.wallatopia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.privacy_policy
import wallatopia.composeapp.generated.resources.privacy_title
import wallatopia.composeapp.generated.resources.terms
import wallatopia.composeapp.generated.resources.terms_title

@Composable
fun PrivacyPolicyScreen(
    onBackPressed: () -> Unit
) {
    val systemBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.eerieBlack)
            .padding(top = systemBarsPadding.calculateTopPadding())
            .verticalScroll(rememberScrollState()),
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
                text = stringResource(Res.string.privacy_title),
                style = AppTheme.typography.pageHeadline,
                color = AppTheme.colorScheme.immutableWhite,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
                    .padding(top = 8.dp)
                    .padding(vertical = 10.dp)
            )
        }
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(Res.string.privacy_policy),
            style = AppTheme.typography.buttonTextSecondary.copy(fontWeight = FontWeight(400)),
            color = AppTheme.colorScheme.immutableWhite,
        )
    }
}