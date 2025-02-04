package uz.droid.wallatopia.presentation.screens.contracts

import androidx.compose.ui.unit.IntSize
import uz.droid.wallatopia.domain.model.ImageUiModel

class ImageGenerateContract {
    data class ImageGenerateState(
        val searchFieldEnabled: Boolean = true,
        val prompt: String = "",
        val generatedImageUrl: String = "",
        val starterImages: List<ImageUiModel> = emptyList()
    )

    sealed interface Intent {
        data object Init : Intent
        data object GenerationFinished : Intent
        data class OnPromptChange(val prompt: String) : Intent
        data class Generate(val screenSize: IntSize) : Intent
        data object StopGenerating : Intent
        data object GenerateAgain : Intent
        data class AddToImageGenerated(val imageUiModel: ImageUiModel): Intent
    }

    sealed interface ImageGenerateProcessState {
        data object Idle : ImageGenerateProcessState
        data object Generating : ImageGenerateProcessState
        data object Ready : ImageGenerateProcessState
    }
}