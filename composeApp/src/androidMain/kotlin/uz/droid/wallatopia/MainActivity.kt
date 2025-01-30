package uz.droid.wallatopia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
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