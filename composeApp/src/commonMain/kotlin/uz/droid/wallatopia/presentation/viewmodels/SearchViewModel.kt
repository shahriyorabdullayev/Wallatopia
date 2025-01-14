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
import uz.droid.wallatopia.presentation.screens.contracts.SearchContract

class SearchViewModel(
    private val repository: MainRepository,
    private val favoriteImagesRepository: FavoritesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchContract.SearchState())
    val uiState: StateFlow<SearchContract.SearchState> = _uiState.asStateFlow()

    init {
        onEventDispatch(SearchContract.Intent.Init)
    }

    fun onEventDispatch(intent: SearchContract.Intent) {
        when (intent) {
            SearchContract.Intent.Init -> {
                handleCategoriesFetch()
            }

            is SearchContract.Intent.Search -> {
                handlePhotoSearch(uiState.value.query)
            }

            is SearchContract.Intent.AddToFavorite -> {
                handleAddToFavorites(intent.imageUiModel)
            }

            SearchContract.Intent.ClearSearch -> {
                handleSearchClear()
            }

            is SearchContract.Intent.OnQueryChange -> {
                _uiState.value = _uiState.value.copy(
                    query = intent.query
                )
            }
        }
    }

    private fun handleSearchClear() {
        _uiState.value = _uiState.value.copy(
            query = "",
            lastQuery = "",
            searchResults = emptyList()
        )
    }

    private fun handlePhotoSearch(query: String) {
        viewModelScope.launch {
            repository.searchPhotos(query).onSuccess {
                _uiState.value =
                    _uiState.value.copy(
                        searchResults = it.results.map { it.toUiModel },
                        lastQuery = query.trim()
                    )
            }
        }
    }

    private fun handleCategoriesFetch() {
        viewModelScope.launch {
            repository.fetchCategories().onSuccess {
                _uiState.value =
                    _uiState.value.copy(categories = it.map { it.toHomeCategoryUiModel })
            }
        }
    }

    private fun handleAddToFavorites(image: ImageUiModel) {
        viewModelScope.launch {
            favoriteImagesRepository.insertImage(image)
        }
    }
}