package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import uz.droid.wallatopia.common.theme.AppTheme

@Composable
fun DetailsActionButton(
    titleRes: StringResource,
    iconRes: DrawableResource,
    tint: Color = AppTheme.colorScheme.immutableWhite,
    sharedAnimationModifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        IconButton(onClick = onClick, enabled = !isLoading) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(38.dp)
                        .then(sharedAnimationModifier)
                        .clip(CircleShape)
                        .background(
                            color = AppTheme.colorScheme.charcoalBlue.copy(alpha = 0.53f),
                            shape = CircleShape
                        )
                        .padding(10.dp),
                    color = tint,
                    strokeWidth = 1.dp,
                    strokeCap = StrokeCap.Butt
                )
            } else {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = "DetailsActionButton",
                    modifier = Modifier
                        .size(38.dp)
                        .then(sharedAnimationModifier)
                        .clip(CircleShape)
                        .background(
                            color = AppTheme.colorScheme.charcoalBlue.copy(alpha = 0.53f),
                            shape = CircleShape
                        )
                        .padding(10.dp),
                    tint = tint
                )
            }
        }
        Text(
            text = stringResource(titleRes),
            style = AppTheme.typography.buttonTextMedium,
            color = AppTheme.colorScheme.immutableWhite,
            modifier = Modifier
                .background(
                    color = AppTheme.colorScheme.charcoalBlue.copy(alpha = 0.53f),
                    shape = AppTheme.shape.rounded10
                )
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}