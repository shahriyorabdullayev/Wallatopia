package uz.droid.wallatopia

import java.util.UUID

actual val randomUUID: String
    get() = UUID.randomUUID().toString()