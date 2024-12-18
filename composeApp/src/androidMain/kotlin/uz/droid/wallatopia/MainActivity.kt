package uz.droid.wallatopia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import uz.droid.wallatopia.common.theme.WallatopiaAppTheme
import uz.droid.wallatopia.presentation.components.MainImageItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val view = LocalView.current
            if (!view.isInEditMode) {
                SideEffect {
                    ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = false
                    ViewCompat.getWindowInsetsController(view)?.isAppearanceLightNavigationBars = false
                }
            }
            App()
        }
    }
}


@Preview
@Composable
fun SplashPr() {
    WallatopiaAppTheme {
        MainImageItem(imageUrl = "", onClick = {})
    }
}