package uz.droid.wallatopia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.droid.wallatopia.domain.repository.FavoritesRepository
import uz.droid.wallatopia.presentation.screens.contracts.ImageDetailsContract

class ImageDetailsViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ImageDetailsContract.ImageDetailsState())
    val uiState: StateFlow<ImageDetailsContract.ImageDetailsState> = _uiState.asStateFlow()

    fun onEventDispatch(intent: ImageDetailsContract.Intent) {
        when (intent) {

            is ImageDetailsContract.Intent.Init -> {
                _uiState.value = _uiState.value.copy(imageUiModel = intent.imageUiModel)
                checkIsFavoriteById(_uiState.value.imageUiModel.id)
            }

            ImageDetailsContract.Intent.HandleInFavorites -> {
                if (_uiState.value.imageUiModel.isFavorite){
                    removeFromFavorites()
                }else{
                    insertInFavorites()
                }
            }
        }
    }

    private fun insertInFavorites() {
        viewModelScope.launch {
            if (_uiState.value.imageUiModel.isAiGenerated) {
                favoritesRepository.updateImage(
                    _uiState.value.imageUiModel.copy(
                        isFavorite = true,
                        timestamp = _uiState.value.imageUiModel.timestamp
                    )
                )
            } else {
                favoritesRepository.insertImage(_uiState.value.imageUiModel.copy(isFavorite = true))
            }
        }
    }

    private fun removeFromFavorites() {
        viewModelScope.launch {
            if (_uiState.value.imageUiModel.isAiGenerated) {
                favoritesRepository.updateImage(
                    _uiState.value.imageUiModel.copy(
                        isFavorite = false,
                        timestamp = _uiState.value.imageUiModel.timestamp
                    )
                )
            } else {
                favoritesRepository.deleteImage(_uiState.value.imageUiModel)
            }
        }
    }

    private fun checkIsFavoriteById(imageId: String) {
        val isFavorite = favoritesRepository.isFavorite(imageId)
        viewModelScope.launch {
            isFavorite.collect { isFavorite ->
                val currentModel = _uiState.value.imageUiModel

                _uiState.value = _uiState.value.copy(
                    imageUiModel = currentModel.copy(isFavorite = isFavorite)
                )
            }
        }
    }
}