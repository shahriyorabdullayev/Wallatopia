package uz.droid.wallatopia.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.currentTimeInMilliSeconds
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.presentation.components.AppButton
import uz.droid.wallatopia.presentation.components.BaseBackground
import uz.droid.wallatopia.presentation.components.ImageGenerateTopBar
import uz.droid.wallatopia.presentation.components.advancedShadow
import uz.droid.wallatopia.presentation.screens.contracts.ImageGenerateContract
import uz.droid.wallatopia.presentation.viewmodels.ImageGenerateViewModel
import uz.droid.wallatopia.randomUUID
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.generate_again
import wallatopia.composeapp.generated.resources.please_wait
import wallatopia.composeapp.generated.resources.set_as_wallpaper
import wallatopia.composeapp.generated.resources.stop

@Composable
fun ImageGenerateScreen(
    onBackPressed: () -> Unit,
    navigateToImageDetails: (String, String) -> Unit
) {

    val viewModel: ImageGenerateViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val generationState by viewModel.generationState.collectAsStateWithLifecycle()
    val event = viewModel::onEventDispatch
    val systemBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)
    val screenSize = remember { mutableStateOf(IntSize(-1, -1)) }

    Layout(
        content = {
            BaseBackground(backgroundImage = Drawables.Images.ImageGenerateBackground) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = systemBarsPadding.calculateTopPadding()
                        ),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    ImageGenerateTopBar(
                        textFieldEnabled = uiState.searchFieldEnabled,
                        modifier = Modifier.padding(start = 15.dp, top = 26.dp, end = 34.dp),
                        prompt = uiState.prompt,
                        onValueChange = {
                            event(ImageGenerateContract.Intent.OnPromptChange(it))
                        },
                        onBack = onBackPressed
                    )

                    if (uiState.generatedImageUrl.isNotBlank()) {
                        GeneratedImage(
                            modifier = Modifier.weight(1f)
                                .padding(vertical = 30.dp)
                                .padding(horizontal = 34.dp),
                            generatedImageUrl = uiState.generatedImageUrl,
                            onImageLoaded = {
                                event(ImageGenerateContract.Intent.GenerationFinished)
                                val generatedImageUrl = uiState.generatedImageUrl
                                val generatedImageUiModel = ImageUiModel(
                                    id = randomUUID,
                                    thumbUrl = generatedImageUrl,
                                    originalUrl = generatedImageUrl,
                                    isFavorite = false,
                                    isAiGenerated = true,
                                    timestamp = currentTimeInMilliSeconds
                                )
                                event(ImageGenerateContract.Intent.AddToImageGenerated(generatedImageUiModel))
                            }
                        )
                    } else if (uiState.starterImages.isNotEmpty()) {
                        StartAiWallpaperSection(
                            modifier = Modifier.weight(1f)
                                .padding(vertical = 60.dp)
                                .padding(horizontal = 34.dp),
                            starterImages = uiState.starterImages,
                            onClick = {

                            }
                        )
                    }

                    when (generationState) {
                        ImageGenerateContract.ImageGenerateProcessState.Idle -> {
                            AppButton(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(horizontal = 34.dp)
                                    .padding(bottom = 70.dp),
                                onClick = {
                                    event(
                                        ImageGenerateContract.Intent.Generate(screenSize = screenSize.value)
                                    )
                                }
                            )
                        }

                        ImageGenerateContract.ImageGenerateProcessState.Generating -> {
                            AppButton(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(horizontal = 34.dp)
                                    .padding(bottom = 70.dp),
                                onClick = {
                                    event(ImageGenerateContract.Intent.StopGenerating)
                                },
                                backgroundColor = AppTheme.colorScheme.vividOrange.copy(.4f),
                                contentColor = AppTheme.colorScheme.vividOrange,
                                text = stringResource(Res.string.stop)
                            )
                        }

                        ImageGenerateContract.ImageGenerateProcessState.Ready -> {
                            AppButton(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(horizontal = 34.dp),
                                onClick = {
                                    navigateToImageDetails(uiState.generatedImageUrl,uiState.generatedImageUrl)
                                },
                                backgroundColor = AppTheme.colorScheme.softWhite,
                                contentColor = AppTheme.colorScheme.eerieBlack,
                                text = stringResource(Res.string.set_as_wallpaper)
                            )
                            AppButton(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(horizontal = 34.dp)
                                    .padding(top = 10.dp, bottom = 40.dp),
                                onClick = {
                                    event(ImageGenerateContract.Intent.GenerateAgain)
                                },
                                text = stringResource(Res.string.generate_again)
                            )
                        }
                    }
                }
            }
        },
        measurePolicy = { measurables, constraints ->
            // Use the max width and height from the constraints
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            screenSize.value = IntSize(width, height)
            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

            layout(width, height) {
                var yPosition = 0
                placeables.forEach { placeable ->
                    placeable.placeRelative(x = 0, y = yPosition)
                    yPosition += placeable.height
                }
            }
        }
    )
}

@Composable
fun StartAiWallpaperSection(
    modifier: Modifier = Modifier,
    starterImages: List<ImageUiModel>,
    onClick: (ImageUiModel) -> Unit
) {
    Column(
        modifier = Modifier.then(modifier),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StartRow(
            modifier = Modifier.weight(1f),
            images = starterImages.take(2),
            onClick = onClick
        )
        StartRow(
            modifier = Modifier.weight(1f),
            images = starterImages.takeLast(2),
            onClick = onClick
        )
    }
}

@Composable
fun StartRow(
    modifier: Modifier = Modifier,
    images: List<ImageUiModel>,
    onClick: (ImageUiModel) -> Unit
) {
    Row(
        modifier = Modifier.then(modifier).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        StartAiWallpaperItem(modifier = Modifier.weight(1f), image = images[0], onClick = onClick)
        StartAiWallpaperItem(modifier = Modifier.weight(1f), image = images[1], onClick = onClick)
    }
}

@Composable
fun StartAiWallpaperItem(
    modifier: Modifier = Modifier,
    image: ImageUiModel,
    onClick: (ImageUiModel) -> Unit
) {
    val placeHolderColor = "FF${image.color.takeLast(6)}".toLong(16)
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(
            LocalPlatformContext.current
        ).data(image.thumbUrl).crossfade(true).build(),
        placeholder = ColorPainter(Color(placeHolderColor)),
        contentScale = ContentScale.Crop
    )

    Box(
        modifier = Modifier
            .then(modifier)
            .clip(AppTheme.shape.rounded15),
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = image.thumbUrl,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .padding(3.dp)
                .clip(AppTheme.shape.circular)
                .background(AppTheme.colorScheme.gainsBoroGray)
                .clickable(
                    onClick = { onClick(image) },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple()
                )
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.GoTo),
                contentDescription = "goto",
                tint = AppTheme.colorScheme.immutableDark
            )
        }
    }
}


@Composable
fun GeneratedImage(
    modifier: Modifier = Modifier,
    generatedImageUrl: String,
    onImageLoaded: () -> Unit
) {
    SubcomposeAsyncImage(
        modifier = Modifier.then(modifier)
            .advancedShadow(
                blur = 13.dp
            )
            .clip(AppTheme.shape.rounded15),
        model = ImageRequest.Builder(
            LocalPlatformContext.current
        )
            .data(generatedImageUrl)
            .crossfade(true).build(),
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        loading = {
            ImageGeneratingPlaceHolder(Modifier.fillMaxSize())
        },
        onSuccess = {
            onImageLoaded()
        }
    )
}

@Composable
fun ImageGeneratingPlaceHolder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.clip(AppTheme.shape.rounded15),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Drawables.Images.ImageGeneratePlaceHolder),
            contentDescription = "placeholder",
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(36.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.please_wait),
                style = AppTheme.typography.bodyText,
                color = AppTheme.colorScheme.immutableWhite,
                textAlign = TextAlign.Center
            )

            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth().height(14.dp),
                color = AppTheme.colorScheme.green00AD9F,
                backgroundColor = AppTheme.colorScheme.gainsBoroGray,
                strokeCap = StrokeCap.Round
            )
        }
    }
}