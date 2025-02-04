package uz.droid.wallatopia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.domain.repository.FavoritesRepository
import uz.droid.wallatopia.presentation.screens.contracts.FavoritesContract

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesContract.FavoritesState())
    val uiState: StateFlow<FavoritesContract.FavoritesState> = _uiState.asStateFlow()

    init {
        onEventDispatch(FavoritesContract.Intent.Init)
    }

    fun onEventDispatch(event: FavoritesContract.Intent) {
        when (event) {
            FavoritesContract.Intent.Init -> {
                fetchFavoriteImages()
            }

            is FavoritesContract.Intent.DeleteFromFavorites -> {
                viewModelScope.launch {
                    if (event.imageUiModel.isAiGenerated) {
                        favoritesRepository.updateImage(event.imageUiModel.copy(isFavorite = false))
                    } else {
                        deleteImageFromFavorites(event.imageUiModel)
                    }
                }
            }
        }
    }

    private fun fetchFavoriteImages() {
        viewModelScope.launch {
            favoritesRepository.fetchFavoriteImages().collect {
                _uiState.value = _uiState.value.copy(favoriteImages = it)
            }
        }
    }

    fun deleteImageFromFavorites(image: ImageUiModel) {
        viewModelScope.launch {
            favoritesRepository.deleteImage(image)
        }
    }

}