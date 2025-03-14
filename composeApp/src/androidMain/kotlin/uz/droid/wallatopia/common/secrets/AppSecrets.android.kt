package uz.droid.wallatopia.common.secrets

import uz.droid.wallatopia.BuildConfig

actual object AppSecrets {
    actual val pixabayApiKey: String
        get() = BuildConfig.pixabayApiKey

}