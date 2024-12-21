package uz.droid.wallatopia.presentation.screens.contracts

import uz.droid.wallatopia.domain.model.CategoryUiModel

class CategoryContract {
    data class CategoryState(
        val categories: List<CategoryUiModel> = emptyList()
    )

    sealed interface Intent {
        data object Init : Intent
    }
}