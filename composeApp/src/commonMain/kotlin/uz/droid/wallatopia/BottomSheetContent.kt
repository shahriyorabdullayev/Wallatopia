package uz.droid.wallatopia

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun ImageDetailsBottomSheetContent(
    modifier: Modifier,
    imageOriginalUrl: String,
    onClose: () -> Unit
)