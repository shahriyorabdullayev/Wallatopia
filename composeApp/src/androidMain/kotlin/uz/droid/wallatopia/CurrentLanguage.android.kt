package uz.droid.wallatopia

import java.util.Locale

actual fun getAppCurrentLanguage(): String {
    return Locale.getDefault().language
}