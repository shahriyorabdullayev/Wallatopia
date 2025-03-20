package uz.droid.wallatopia

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.presentation.components.AppButton
import uz.droid.wallatopia.presentation.screens.SetScreenTypeItem
import uz.droid.wallatopia.presentation.screens.SetScreenTypeItemDivider
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.close
import wallatopia.composeapp.generated.resources.download_success
import wallatopia.composeapp.generated.resources.high_quality
import wallatopia.composeapp.generated.resources.low_quality

@Composable
actual fun ImageDetailsBottomSheetContent(
    modifier: Modifier,
    imageByteArray: ByteArray,
    onActionStart: (message: String) -> Unit,
    onActionSuccess: (message: String) -> Unit,
    onClose: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val successMessage = stringResource(Res.string.download_success)

    Column(
        Modifier
            .then(modifier)
            .fillMaxWidth()
            .padding(vertical = 32.dp)
    ) {
        SetScreenTypeItem(
            icon = Drawables.Icons.DownloadWhite,
            title = Res.string.high_quality,
            onClick = {
//                onActionStart()
                scope.launch {
                    saveImageToGallery(
                        image = imageByteArray,
                        onSuccess = {
                            onActionSuccess(successMessage)
                        },
                        onFailure = {}
                    )
                }
            }
        )
        SetScreenTypeItemDivider()
        SetScreenTypeItem(
            icon = Drawables.Icons.DownloadWhite,
            title = Res.string.low_quality,
            onClick = {
                scope.launch {

                }
            })

        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp, end = 24.dp, top = 12.dp),
            backgroundColor = AppTheme.colorScheme.radicalRed.copy(.24f),
            contentColor = AppTheme.colorScheme.immutableWhite,
            text = stringResource(Res.string.close),
            shape = AppTheme.shape.rounded10,
            textStyle = AppTheme.typography.buttonTextLarge,
            onClick = onClose
        )
    }
}