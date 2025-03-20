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
import org.koin.compose.koinInject
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.presentation.components.AppButton
import uz.droid.wallatopia.presentation.screens.SetScreenTypeItem
import uz.droid.wallatopia.presentation.screens.SetScreenTypeItemDivider
import uz.droid.wallatopia.wallpaper_utils.WallatopiaWallpaperManager
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.close
import wallatopia.composeapp.generated.resources.set_both_screens
import wallatopia.composeapp.generated.resources.set_home_screen
import wallatopia.composeapp.generated.resources.set_lock_screen

@Composable
actual fun ImageDetailsBottomSheetContent(
    modifier: Modifier,
    imageByteArray: ByteArray,
    onActionStart: (message: String) -> Unit,
    onActionSuccess: (message: String) -> Unit,
    onClose: () -> Unit
) {
    val myWallpaperManager: WallatopiaWallpaperManager = koinInject()
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .then(modifier)
            .fillMaxWidth()
            .padding(vertical = 32.dp)
    ) {
        SetScreenTypeItem(
            icon = Drawables.Icons.HomeScreen,
            title = Res.string.set_home_screen,
            onClick = {
                scope.launch {
                    myWallpaperManager.setOnHomeScreen(imageByteArray)
                }
            }
        )
        SetScreenTypeItemDivider()
        SetScreenTypeItem(
            icon = Drawables.Icons.LockScreen,
            title = Res.string.set_lock_screen,
            onClick = {
                scope.launch {
                    myWallpaperManager.setOnLockScreen(imageByteArray)
                }
            })
        SetScreenTypeItemDivider()
        SetScreenTypeItem(
            icon = Drawables.Icons.BothScreens,
            title = Res.string.set_both_screens,
            onClick = {
                scope.launch {
                    myWallpaperManager.setOnBothScreens(imageByteArray)
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