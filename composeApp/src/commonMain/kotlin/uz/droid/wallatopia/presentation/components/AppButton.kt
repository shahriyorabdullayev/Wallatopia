package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import uz.droid.wallatopia.common.theme.AppTheme

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = AppTheme.colorScheme.green00AD9F,
    contentColor: Color = AppTheme.colorScheme.immutableWhite,
    text: String = "Start Generating",
    textStyle: TextStyle = AppTheme.typography.buttonTextPrimary,
    shape: Shape = AppTheme.shape.rounded7,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 12.dp),
        onClick = onClick,
        elevation = null,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = text,
            style = textStyle,
        )
    }
}