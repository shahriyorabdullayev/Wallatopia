package uz.droid.wallatopia

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.common.theme.WallatopiaAppTheme
import uz.droid.wallatopia.presentation.screens.SplashScreen

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
        SplashScreen {

        }
    }
}