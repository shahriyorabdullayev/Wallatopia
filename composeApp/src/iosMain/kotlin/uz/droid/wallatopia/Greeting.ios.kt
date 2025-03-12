package uz.droid.wallatopia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import uz.droid.wallatopia.common.theme.AppTheme
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.soon

@Composable
actual fun ImageDetailsBottomSheetContent(
    modifier: Modifier,
    imageOriginalUrl: String,
    onClose: () -> Unit
) {
    Box(
        Modifier.fillMaxWidth().height(300.dp).background(AppTheme.colorScheme.immutableWhite),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(Res.string.soon),
            style = AppTheme.typography.splashTitle
        )
    }
}