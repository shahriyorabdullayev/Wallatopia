package uz.droid.wallatopia

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

actual val currentTimeInMilliSeconds: Long
    get() = (NSDate().timeIntervalSince1970 * 1000).toLong()