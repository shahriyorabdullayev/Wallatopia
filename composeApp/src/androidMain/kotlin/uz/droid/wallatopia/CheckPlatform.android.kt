package uz.droid.wallatopia

import android.os.Build

actual val isAndroid: Boolean
    get() = true
actual val isVersionBelow12: Boolean
    get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.S