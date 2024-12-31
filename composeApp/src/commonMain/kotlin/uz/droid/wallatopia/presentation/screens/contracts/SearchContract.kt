package uz.droid.wallatopia.presentation.screens.contracts

import uz.droid.wallatopia.domain.model.CategoryUiModel
import uz.droid.wallatopia.domain.model.ImageUiModel

class SearchContract {
    data class SearchState(
        val query: String = "",
        val lastQuery: String = "",
        val categories: List<CategoryUiModel> = emptyList(),
        val searchResults: List<ImageUiModel> = emptyList()
    )

    sealed interface Intent {
        data object Init : Intent
        data class OnQueryChange(val query: String) : Intent
        data object Search : Intent
        data object ClearSearch : Intent
        data class AddToFavorite(val imageUiModel: ImageUiModel) : Intent
    }
}