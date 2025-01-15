package uz.droid.wallatopia

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual fun getAppCurrentLanguage(): String {
    return NSLocale.currentLocale.languageCode
}