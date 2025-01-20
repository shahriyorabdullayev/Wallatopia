package uz.droid.wallatopia.domain.repository


interface SettingsRepository {
    fun setLanguage(language: String)
    fun getCurrentLanguage(): String
}