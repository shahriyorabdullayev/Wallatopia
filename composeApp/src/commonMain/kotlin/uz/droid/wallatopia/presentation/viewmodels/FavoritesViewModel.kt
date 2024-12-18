package uz.droid.wallatopia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.domain.repository.FavoritesRepository

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
): ViewModel() {

    val state = MutableStateFlow(emptyList<ImageUiModel>())

    init {
        fetchFavoriteImages()
    }

    private fun fetchFavoriteImages() {
        viewModelScope.launch {
            favoritesRepository.fetchFavoriteImages().collect {
                state.value = it
            }
        }
    }

    fun deleteImageFromFavorites(image: ImageUiModel) {
        viewModelScope.launch {
            favoritesRepository.deleteImage(image)
        }
    }

}