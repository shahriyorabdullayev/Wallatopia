package uz.droid.wallatopia.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import uz.droid.wallatopia.common.theme.AppTheme

@Composable
fun ScrollToTopButton(
    modifier: Modifier = Modifier,
    scrollTopVisible: Boolean = false,
    onClick: () -> Unit = {},
) {

    AnimatedVisibility(
        visible = scrollTopVisible,
        enter = fadeIn() + slideInHorizontally(initialOffsetX = { it }),
        exit = fadeOut() + slideOutHorizontally(targetOffsetX = { it }),
        modifier = modifier
    ) {
        IconButton(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(AppTheme.colorScheme.charlestonGray),
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = null,
                tint = AppTheme.colorScheme.immutableWhite
            )
        }
    }
}