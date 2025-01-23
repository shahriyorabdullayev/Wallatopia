package uz.droid.wallatopia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.droid.wallatopia.data.mapper.toHomeCategoryUiModel
import uz.droid.wallatopia.data.mapper.toUiModel
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.domain.repository.FavoritesRepository
import uz.droid.wallatopia.domain.repository.MainRepository
import uz.droid.wallatopia.presentation.screens.contracts.HomeContract

class HomeViewModel(
    private val repository: MainRepository,
    private val favoriteImagesRepository: FavoritesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeContract.HomeState())
    val uiState: StateFlow<HomeContract.HomeState> = _uiState.asStateFlow()

    private var favorites = emptyList<ImageUiModel>()

    init {
        onEventDispatch(HomeContract.Intent.Init)
    }

    fun onEventDispatch(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.AddToFavorites -> {
                handleAddToFavorites(intent.imageUiModel)
            }

            is HomeContract.Intent.OpenImage -> {

            }

            is HomeContract.Intent.DeleteFromFavorites -> {
                viewModelScope.launch {
                    favoriteImagesRepository.deleteImage(intent.imageUiModel)
                }
            }

            HomeContract.Intent.Init -> {
                viewModelScope.launch {
                    repository.fetchWallpapersFromPixabay().onSuccess { response ->
                        favoriteImagesRepository.fetchFavoriteImages().collect {
                            _uiState.value = _uiState.value.copy(images = response.hits.map {
                                it.toUiModel(
                                    isFavorite = favoriteImagesRepository.isFavorite(it.id.toString())
                                )
                            })
                        }
                    }
                }
//                handleWallpaperFetch()
//                handleCategoriesFetch()
            }
        }
    }

    private fun handleWallpaperFetch() {
        viewModelScope.launch {
            favoriteImagesRepository.fetchFavoriteImages().collect {
                viewModelScope.launch {
                    repository.fetchWallpapers().onSuccess { images ->
                        _uiState.value = _uiState.value.copy(images = images.map {
                            it.toUiModel(isFavorite = favoriteImagesRepository.isFavorite(it.id))
                        })
                    }
                }
            }
        }
    }

    private fun handleCategoriesFetch() {
        viewModelScope.launch {
            repository.fetchCategories().onSuccess {
                _uiState.value =
                    _uiState.value.copy(categories = it.take(4).map { it.toHomeCategoryUiModel })
            }
        }
    }

    private fun handleAddToFavorites(image: ImageUiModel) {
        viewModelScope.launch {
            favoriteImagesRepository.insertImage(image)
        }
    }
}