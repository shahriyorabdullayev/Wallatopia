package uz.droid.wallatopia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import uz.droid.wallatopia.data.paging.PixabayPagingSource
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

    init {
        onEventDispatch(HomeContract.Intent.Init)
    }

    fun onEventDispatch(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.AddToFavorites -> {
                viewModelScope.launch {
                    if (intent.imageUiModel.isAiGenerated) {
                        favoriteImagesRepository.updateImage(intent.imageUiModel.copy(isFavorite = true, timestamp = intent.imageUiModel.timestamp))
                    } else {
                        favoriteImagesRepository.insertImage(intent.imageUiModel.copy(isFavorite = true))
                    }
                }
            }

            is HomeContract.Intent.SelectTab -> {
                _uiState.value = uiState.value.copy(selectedTabIndex = intent.index)
            }

            is HomeContract.Intent.DeleteFromFavorites -> {
                viewModelScope.launch {
                    if (intent.imageUiModel.isAiGenerated) {
                        favoriteImagesRepository.updateImage(intent.imageUiModel.copy(isFavorite = false, timestamp = intent.imageUiModel.timestamp))
                    } else {
                        favoriteImagesRepository.deleteImage(intent.imageUiModel)
                    }
                }
            }

            HomeContract.Intent.Init -> {
                handleCategoriesFetch()
                val pagingResponseFlow = Pager(
                    config = PagingConfig(
                        pageSize = 20,
                        enablePlaceholders = false
                    ),
                    pagingSourceFactory = { PixabayPagingSource(repository) }
                ).flow.cachedIn(viewModelScope)

                val favoriteImagesFlow = favoriteImagesRepository.fetchFavoriteImages()

                val combinedFlow =
                    combine(
                        pagingResponseFlow,
                        favoriteImagesFlow
                    ) { pagingData, favoriteList ->
                        pagingData.map {
                            it.toUiModel(isFavorite = favoriteImagesRepository.isFavorite(it.id.toString()))
                        }
                    }.cachedIn(viewModelScope)


                _uiState.value = uiState.value.copy(homeImages = combinedFlow)

                viewModelScope.launch {
                    favoriteImagesRepository.fetchAiGeneratedImages().collect {
                        _uiState.value = uiState.value.copy(aiGeneratedImages = it)
                    }
                }
            }
        }
    }

    private fun handleCategoriesFetch() {
        viewModelScope.launch {
            repository.fetchCategories().onSuccess {
                _uiState.value =
                    _uiState.value.copy(categories = it.take(4))
            }
        }
    }

    private fun handleAddToFavorites(image: ImageUiModel) {
        viewModelScope.launch {

        }
    }
}