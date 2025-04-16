package uz.droid.wallatopia.kaptura

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.get
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.create


@OptIn(ExperimentalForeignApi::class)
internal fun NSData?.toByteArray(): ByteArray? {
    return if (this == null) {
        null
    } else {
        if (bytes == null) {
            null
        } else {
            val pointer: CPointer<ByteVar> = bytes!!.reinterpret()
            ByteArray(length.toInt()) { index -> pointer[index].toByte() }
        }
    }
}


@OptIn(ExperimentalForeignApi::class)
internal fun ByteArray.toNSData(): NSData {
    return usePinned { pinned ->
        NSData.create(bytes = pinned.addressOf(0), length = this.size.toULong())
    }
}