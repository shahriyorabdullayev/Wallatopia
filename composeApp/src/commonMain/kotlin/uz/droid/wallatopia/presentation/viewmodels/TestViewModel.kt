package uz.droid.wallatopia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.droid.wallatopia.data.network.response.UnsplashResponse
import uz.droid.wallatopia.domain.model.UnsplashImage
import uz.droid.wallatopia.domain.repository.FavoritesRepository
import uz.droid.wallatopia.domain.repository.MainRepository

class TestViewModel(
    private val repository: MainRepository,
    private val favoriteImagesRepository: FavoritesRepository
) : ViewModel() {

    val state = MutableStateFlow(emptyList<UnsplashResponse>())

    init {
        viewModelScope.launch {
            repository.fetchWallpapers().onSuccess {
                state.value = it
            }
        }
    }

    fun addToFavorites(image: UnsplashImage) {
        viewModelScope.launch {
            favoriteImagesRepository.insertUnsplashImage(image)
        }
    }
}