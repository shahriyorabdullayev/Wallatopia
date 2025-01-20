package uz.droid.wallatopia.presentation.screens.contracts

class SettingsContract {
    data class SettingsState(
        val language: String = "en",
    )

    sealed interface Intent{
        object Init: Intent
        data class ChangeLanguage(val language: String): Intent
    }
}