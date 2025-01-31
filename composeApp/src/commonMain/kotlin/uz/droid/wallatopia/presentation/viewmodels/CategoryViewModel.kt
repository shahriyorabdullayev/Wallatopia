package uz.droid.wallatopia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.droid.wallatopia.domain.repository.MainRepository
import uz.droid.wallatopia.presentation.screens.contracts.CategoryContract

class CategoryViewModel(
    private val repository: MainRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CategoryContract.CategoryState())
    val uiState: StateFlow<CategoryContract.CategoryState> = _uiState.asStateFlow()

    init {
        onEventDispatch(CategoryContract.Intent.Init)
    }

    private fun onEventDispatch(intent: CategoryContract.Intent) {
        when (intent) {
            CategoryContract.Intent.Init -> {
                handleCategoriesFetch()
            }
        }
    }

    private fun handleCategoriesFetch() {
        viewModelScope.launch {
            repository.fetchCategories().onSuccess {
                _uiState.value =
                    _uiState.value.copy(categories = it.take(15))
            }
        }
    }
}