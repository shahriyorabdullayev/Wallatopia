package uz.droid.wallatopia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.droid.wallatopia.common.Constants.GENERATIVE_MODELS
import uz.droid.wallatopia.data.mapper.toUiModel
import uz.droid.wallatopia.domain.repository.ImageGenerateRepository
import uz.droid.wallatopia.domain.repository.MainRepository
import uz.droid.wallatopia.presentation.screens.contracts.ImageGenerateContract

class ImageGenerateViewModel(
    private val imageGenerateRepository: ImageGenerateRepository,
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ImageGenerateContract.ImageGenerateState())
    val uiState: StateFlow<ImageGenerateContract.ImageGenerateState> = _uiState.asStateFlow()

    private val _generationState: MutableStateFlow<ImageGenerateContract.ImageGenerateProcessState> =
        MutableStateFlow(ImageGenerateContract.ImageGenerateProcessState.Idle)
    val generationState: StateFlow<ImageGenerateContract.ImageGenerateProcessState> =
        _generationState.asStateFlow()

    init {
        onEventDispatch(ImageGenerateContract.Intent.Init)
    }

    fun onEventDispatch(intent: ImageGenerateContract.Intent) {
        when (intent) {
            ImageGenerateContract.Intent.Init -> {
                handleInitialSearch()
            }

            is ImageGenerateContract.Intent.Generate -> {
                _generationState.value = ImageGenerateContract.ImageGenerateProcessState.Generating
                val imageUrl =
                    "https://image.pollinations.ai/prompt/${uiState.value.prompt}/${GENERATIVE_MODELS[1]}?width=${intent.screenSize.width}&height=${intent.screenSize.height}&safe=true"

                _uiState.value = _uiState.value.copy(
                    generatedImageUrl = imageUrl,
                    searchFieldEnabled = false
                )
            }

            is ImageGenerateContract.Intent.OnPromptChange -> {
                _uiState.value = _uiState.value.copy(
                    prompt = intent.prompt
                )
            }

            ImageGenerateContract.Intent.GenerationFinished -> {
                _generationState.value = ImageGenerateContract.ImageGenerateProcessState.Ready
                _uiState.value = _uiState.value.copy(
                    searchFieldEnabled = false
                )
            }

            ImageGenerateContract.Intent.GenerateAgain -> {
                _generationState.value = ImageGenerateContract.ImageGenerateProcessState.Idle
                _uiState.value = _uiState.value.copy(
                    generatedImageUrl = "",
                    searchFieldEnabled = true
                )
            }

            ImageGenerateContract.Intent.StopGenerating -> {
                _generationState.value = ImageGenerateContract.ImageGenerateProcessState.Idle
                _uiState.value = _uiState.value.copy(
                    generatedImageUrl = "",
                    searchFieldEnabled = true
                )
            }
        }
    }

    private fun handleInitialSearch() {
        viewModelScope.launch {
            mainRepository.searchPhotos("abstract art").onSuccess {
                _uiState.value =
                    _uiState.value.copy(
                        starterImages = it.results.take(4).map { it.toUiModel }
                    )
            }
        }
    }
}