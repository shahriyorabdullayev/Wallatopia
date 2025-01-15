package uz.droid.wallatopia.presentation.screens.contracts

class SettingsContract {
    data class SettingsState(
        val language: String = "English",
    )

    sealed interface Intent{
        object Init: Intent
        object LanguageChanged: Intent
    }
}