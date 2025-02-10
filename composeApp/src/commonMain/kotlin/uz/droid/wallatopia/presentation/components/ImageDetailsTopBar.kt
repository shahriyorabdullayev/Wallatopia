package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.wallpapers

@Composable
fun ImageDetailsTopBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onBackPressed,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Back),
                contentDescription = "Back icon",
                tint = AppTheme.colorScheme.immutableWhite
            )
        }
        Text(
            text = stringResource(Res.string.wallpapers),
            style = AppTheme.typography.pageHeadline,
            color = AppTheme.colorScheme.immutableWhite,
            modifier = Modifier
        )
    }
}