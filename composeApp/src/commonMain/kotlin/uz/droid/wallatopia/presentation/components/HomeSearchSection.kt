package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.search_for_wallpapers

@Composable
fun HomeSearchSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(AppTheme.shape.rounded10)
            .background(AppTheme.colorScheme.jetGray)
            .advancedShadow(
                offsetY = 1.dp,
                blur = 11.dp
            )
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            )
            .padding(start = 18.dp, end = 25.dp)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.search_for_wallpapers),
            color = AppTheme.colorScheme.ashGray,
            style = AppTheme.typography.hintText
        )

        Image(
            painter = painterResource(Drawables.Icons.Search),
            contentDescription = "Search For Wallpapers",
            colorFilter = ColorFilter.tint(AppTheme.colorScheme.sonicSilverGray)
        )
    }
}