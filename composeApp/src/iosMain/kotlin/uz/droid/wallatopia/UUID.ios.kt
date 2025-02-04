package uz.droid.wallatopia

import platform.Foundation.NSUUID

actual val randomUUID: String
    get() = NSUUID().UUIDString()