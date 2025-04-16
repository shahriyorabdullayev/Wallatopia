package uz.droid.wallatopia.kaptura

import java.security.MessageDigest

actual class UrlEncoder {
    actual fun encode(url: String): String {
        val digest = MessageDigest.getInstance("MD5")
        val hash = digest.digest(url.toByteArray())
        return hash.joinToString("") { "%02x".format(it) }
    }
}