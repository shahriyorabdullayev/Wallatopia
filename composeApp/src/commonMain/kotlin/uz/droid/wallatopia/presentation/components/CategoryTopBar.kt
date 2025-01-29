package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.categories_title

@Composable
fun CategoryTopBar(
    modifier: Modifier,
    title: String = stringResource(Res.string.categories_title),
    onBack: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.then(modifier)
            .clickable(onClick = onBack, interactionSource = null, indication = null)
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Back),
            contentDescription = title,
            tint = AppTheme.colorScheme.immutableWhite
        )
        Text(
            text = title,
            style = AppTheme.typography.pageHeadline,
            color = AppTheme.colorScheme.immutableWhite
        )
    }
}