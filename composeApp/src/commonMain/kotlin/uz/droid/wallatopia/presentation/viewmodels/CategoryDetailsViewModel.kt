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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import uz.droid.wallatopia.data.mapper.toUiModel
import uz.droid.wallatopia.data.paging.PixabaySearchPagingSource
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
            is CategoryDetailsContract.Intent.LoadCategoryImages -> {
                handleCategoriesFetch(intent.categoryName)
            }

            is CategoryDetailsContract.Intent.AddToFavorite -> {
                handleAddToFavorites(intent.imageUiModel)
            }
        }
    }

    private fun handleCategoriesFetch(categoryName: String) {
        viewModelScope.launch {
            val pagingResponse = Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { PixabaySearchPagingSource(repository, categoryName) }
            ).flow
                .map { pagingData ->
                    pagingData.map {
                        it.toUiModel
                    }
                }.cachedIn(viewModelScope)

            _uiState.value = uiState.value.copy(
                categoryPhotos = pagingResponse
            )
        }
    }

    private fun handleAddToFavorites(image: ImageUiModel) {
        viewModelScope.launch {
            favoriteImagesRepository.insertImage(image)
        }
    }
}