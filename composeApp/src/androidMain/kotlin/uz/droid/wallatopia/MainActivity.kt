package uz.droid.wallatopia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import uz.droid.wallatopia.common.theme.WallatopiaAppTheme
import uz.droid.wallatopia.presentation.screens.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
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