package uz.droid.wallatopia.data.repository

import com.russhwolf.settings.Settings
import uz.droid.wallatopia.common.preference.StringSettingConfig
import uz.droid.wallatopia.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    settings: Settings
): SettingsRepository {
    private val _appLanguage = StringSettingConfig(settings, "language", "en")

    override fun setLanguage(language: String) {
        _appLanguage.set(language)
    }

    override fun getCurrentLanguage(): String = _appLanguage.get()
}