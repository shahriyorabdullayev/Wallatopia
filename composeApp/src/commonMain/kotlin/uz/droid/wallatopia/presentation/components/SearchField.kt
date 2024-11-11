package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(AppTheme.shape.rounded10)
            .background(AppTheme.colorScheme.jetGray)
            .clickable(onClick = onClick)
            .padding(start = 18.dp, end = 25.dp)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Search For Wallpapers",
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