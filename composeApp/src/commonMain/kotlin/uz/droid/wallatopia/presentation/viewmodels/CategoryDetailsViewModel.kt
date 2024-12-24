package uz.droid.wallatopia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.droid.wallatopia.data.mapper.toUiModel
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.domain.repository.FavoritesRepository
import uz.droid.wallatopia.domain.repository.MainRepository
import uz.droid.wallatopia.presentation.screens.contracts.CategoryDetailsContract

class CategoryDetailsViewModel(
    private val repository: MainRepository,
    private val favoriteImagesRepository: FavoritesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CategoryDetailsContract.CategoryDetailsState())
    val uiState: StateFlow<CategoryDetailsContract.CategoryDetailsState> = _uiState.asStateFlow()

    fun onEventDispatch(intent: CategoryDetailsContract.Intent) {
        when (intent) {
            is CategoryDetailsContract.Intent.Init -> {
                handleCategoriesFetch(intent.categoryId)
            }

            is CategoryDetailsContract.Intent.AddToFavorite -> {
                handleAddToFavorites(intent.imageUiModel)
            }
        }
    }

    private fun handleCategoriesFetch(categoryId: String) {
        viewModelScope.launch {
            repository.fetchCategoryPhotos(categoryId).onSuccess {
                _uiState.value =
                    _uiState.value.copy(categoryPhotos = it.map { it.toUiModel })
            }
        }
    }

    private fun handleAddToFavorites(image: ImageUiModel) {
        viewModelScope.launch {
            favoriteImagesRepository.insertImage(image)
        }
    }
}