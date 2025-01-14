package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import coil3.Extras
import org.jetbrains.compose.resources.painterResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.data.network.response.SuggestionResponse

@Composable
fun SearchTopBar(
    modifier: Modifier,
    query: String,
    title: String = "Categories",
    suggestions: List<String> = emptyList(),
    onBack: () -> Unit,
    onValueChange: (String) -> Unit,
    onSearch: (query: String) -> Unit
) {
    var showSuggestions by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

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
        Column(
            modifier = Modifier
                .advancedShadow(blur = 7.dp)
                .clip(AppTheme.shape.rounded10)
                .background(AppTheme.colorScheme.jetGray)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 18.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = query,
                    onValueChange = {
                        onValueChange(it)
                        showSuggestions = it.length > 2
                    },
                    modifier = Modifier.weight(1f).focusRequester(focusRequester),
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
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearch(query)
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
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
            if (showSuggestions) {
                Popup(
                    alignment = Alignment.TopCenter,
                    offset = IntOffset(x = 0, y = statusBarHeight.value.toInt()+100),
                    onDismissRequest = {
                        showSuggestions = false
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .advancedShadow(blur = 5.dp)
                            .background(AppTheme.colorScheme.jetGray, shape = AppTheme.shape.rounded10)
                            .width(280.dp)
                            .padding(vertical = 4.dp)
                    ) {
                        suggestions.forEachIndexed { index, suggestion ->
                            SuggestionItem(
                                suggestion = suggestion,
                                onClick = {
                                    onValueChange(suggestion)
                                    focusManager.clearFocus()
                                    keyboardController?.hide()
                                    showSuggestions = false
                                    onSearch(query)
                                }
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun SuggestionItem(
    suggestion: String = "",
    onClick: () -> Unit = {}
) {
    Text(
        text = suggestion,
        style = AppTheme.typography.inputText,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(start = 8.dp)
            .padding(vertical = 8.dp),
        textAlign = TextAlign.Start
    )
}