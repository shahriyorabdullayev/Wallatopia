package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.abstract_wallpaper

@Composable
fun ImageGenerateTopBar(
    textFieldEnabled: Boolean,
    modifier: Modifier,
    prompt: String,
    title: String = stringResource(Res.string.abstract_wallpaper),
    onBack: () -> Unit,
    onValueChange: (String) -> Unit
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
            enabled = textFieldEnabled,
            modifier = Modifier
                .weight(1f)
                .advancedShadow(
                    offsetY = 1.dp,
                    blur = 11.dp
                ),
            value = prompt,
            hintText = stringResource(Res.string.abstract_wallpaper),
            endIcon = {
                Icon(
                    painter = painterResource(Drawables.Icons.AiGenerate),
                    contentDescription = "Generative AI",
                    tint = AppTheme.colorScheme.green00F798
                )
            },
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
    }
}