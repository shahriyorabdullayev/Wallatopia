package uz.droid.wallatopia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.droid.wallatopia.domain.repository.SettingsRepository
import uz.droid.wallatopia.presentation.screens.contracts.SettingsContract

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsContract.SettingsState())
    val uiState: StateFlow<SettingsContract.SettingsState> = _uiState.asStateFlow()

    init {
        onEventDispatch(SettingsContract.Intent.Init)
    }

    fun onEventDispatch(intent: SettingsContract.Intent) {
        when (intent) {
            is SettingsContract.Intent.ChangeLanguage -> {
                _uiState.value = _uiState.value.copy(language = intent.language)
                settingsRepository.setLanguage(intent.language)
            }
            SettingsContract.Intent.Init -> {
                _uiState.value = _uiState.value.copy(language = settingsRepository.getCurrentLanguage())
            }
        }
    }
}