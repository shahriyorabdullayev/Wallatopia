package uz.droid.wallatopia

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

actual class ShareManager {
    actual fun share(url:String) {
        val activityViewController = UIActivityViewController(listOf(url), null)
        UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
            activityViewController, animated = true, completion = null
        )
    }
}

@Composable
actual fun rememberShareManager(): ShareManager {
    return remember { ShareManager() }
}