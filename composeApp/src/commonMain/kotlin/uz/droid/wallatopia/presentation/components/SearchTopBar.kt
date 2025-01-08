package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme

@Composable
fun SearchTopBar(
    modifier: Modifier,
    query: String,
    title: String = "Categories",
    onBack: () -> Unit,
    onValueChange: (String) -> Unit,
    onSearch: (query: String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(11.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.then(modifier)
            .clickable(onClick = onBack, interactionSource = null, indication = null)
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Back),
            contentDescription = title,
            tint = AppTheme.colorScheme.immutableWhite
        )
        Row(
            modifier = Modifier
                .advancedShadow(
                    blur = 7.dp
                )
                .clip(AppTheme.shape.rounded10)
                .background(AppTheme.colorScheme.jetGray)
                .padding(start = 18.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = query,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->
                    if (query.isEmpty()) {
                        Text(
                            "Search For Wallpapers",
                            color = AppTheme.colorScheme.ashGray,
                            style = AppTheme.typography.hintText
                        )
                    }
                    innerTextField()
                },
                textStyle = AppTheme.typography.inputText.copy(
                    color = AppTheme.colorScheme.chineseSilverGray
                )
            )

            IconButton(onClick = {
                onSearch(query)
            }) {
                Icon(
                    painter = painterResource(Drawables.Icons.Search),
                    contentDescription = "Search For Wallpapers",
                    tint = AppTheme.colorScheme.chineseSilverGray
                )
            }
        }
    }
}