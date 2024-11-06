package uz.droid.wallatopia

import androidx.compose.ui.window.ComposeUIViewController
import uz.droid.wallatopia.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }