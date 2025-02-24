package uz.droid.wallatopia

import androidx.compose.runtime.Composable

expect class ShareManager {
    fun share(url:String)
}

@Composable
expect fun rememberShareManager(): ShareManager