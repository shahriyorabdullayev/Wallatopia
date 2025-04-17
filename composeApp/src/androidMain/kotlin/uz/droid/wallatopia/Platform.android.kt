package uz.droid.wallatopia

import android.os.Build
import java.util.UUID

actual val currentTimeInMilliSeconds: Long
    get() = System.currentTimeMillis()

actual val randomUUID: String
    get() = UUID.randomUUID().toString()

actual val AppStoreLink: String
    get() = "https://play.google.com/store/apps/details?id=uz.droid.wallatopia&hl=en"

actual val isAndroid: Boolean
    get() = true
actual val isVersionBelow12: Boolean
    get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.S