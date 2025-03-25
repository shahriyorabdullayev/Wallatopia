package uz.droid.wallatopia

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun ImageDetailsBottomSheetContent(
    modifier: Modifier,
    imageByteArray: ByteArray,
    onActionStart: (message: String) -> Unit,
    onActionSuccess: (message: String) -> Unit,
    onClose: () -> Unit
)