package uz.droid.wallatopia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.droid.wallatopia.presentation.screens.contracts.SettingsContract

class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsContract.SettingsState())
    val uiState: StateFlow<SettingsContract.SettingsState> = _uiState.asStateFlow()

    fun onEventDispatch(intent: SettingsContract.Intent) {
        when (intent) {
            SettingsContract.Intent.LanguageChanged -> {

            }

            SettingsContract.Intent.Init -> {

            }
        }
    }
}