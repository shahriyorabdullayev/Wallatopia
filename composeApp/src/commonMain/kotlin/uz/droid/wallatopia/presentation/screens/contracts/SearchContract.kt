package uz.droid.wallatopia.presentation.screens.contracts

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import uz.droid.wallatopia.domain.model.CategoryUiModel
import uz.droid.wallatopia.domain.model.ImageUiModel

class SearchContract {
    data class SearchState(
        val query: String = "",
        val lastQuery: String = "",
        val categories: List<CategoryUiModel> = emptyList(),
        val searchResults: Flow<PagingData<ImageUiModel>> = emptyFlow(),
        val suggestions: List<String> = emptyList()
    )

    sealed interface Intent {
        data object Init : Intent
        data class OnQueryChange(val query: String) : Intent
        data object Search : Intent
        data object ClearSearch : Intent
        data class AddToFavorite(val imageUiModel: ImageUiModel) : Intent
    }
}