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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
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
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.then(modifier)
    ) {
        IconButton(onClick = onBack) {
            Icon(
                painter = painterResource(Drawables.Icons.Back),
                contentDescription = title,
                tint = AppTheme.colorScheme.immutableWhite
            )
        }
        AppSearchField(
            modifier = Modifier
                .weight(1f)
                .advancedShadow(
                    blur = 7.dp
                ),
            value = query,
            endIcon = {
                Icon(
                    painter = painterResource(Drawables.Icons.Search),
                    contentDescription = "Search",
                    tint = AppTheme.colorScheme.chineseSilverGray
                )
            },
            endIconClickEnabled = true,
            onValueChange = onValueChange,
            hintText = "Search For Wallpapers",
            onDoneAction = onSearch
        )
    }
}

@Composable
fun AppSearchField(
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    endIcon: @Composable () -> Unit,
    value: String,
    hintText: String,
    endIconClickEnabled: Boolean = false,
    onValueChange: (String) -> Unit,
    onDoneAction: (value: String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .then(modifier)
            .clip(AppTheme.shape.rounded10)
            .background(AppTheme.colorScheme.jetGray)
            .padding(start = 18.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            enabled = enabled,
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        hintText,
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

        IconButton(
            enabled = endIconClickEnabled,
            onClick = {
                onDoneAction(value)
            }
        ) {
            endIcon()
        }
    }
}