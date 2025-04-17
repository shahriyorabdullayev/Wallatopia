package uz.droid.wallatopia

import platform.Foundation.NSDate
import platform.Foundation.NSUUID
import platform.Foundation.timeIntervalSince1970

actual val currentTimeInMilliSeconds: Long
    get() = (NSDate().timeIntervalSince1970 * 1000).toLong()

actual val randomUUID: String
    get() = NSUUID().UUIDString()

actual val AppStoreLink: String
    get() = "https://play.google.com/store/apps/details?id=uz.droid.wallatopia&hl=en"

actual val isAndroid: Boolean
    get() = false
actual val isVersionBelow12: Boolean
    get() = false