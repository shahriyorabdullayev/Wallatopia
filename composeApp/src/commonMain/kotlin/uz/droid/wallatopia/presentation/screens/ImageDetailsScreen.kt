package uz.droid.wallatopia.presentation.screens

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import uz.droid.wallatopia.LocalAnimatedVisibility
import uz.droid.wallatopia.LocalSharedTransition
import org.koin.compose.viewmodel.koinViewModel
import uz.droid.wallatopia.ImageDetailsBottomSheetContent
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.isAndroid
import uz.droid.wallatopia.isVersionBelow12
import uz.droid.wallatopia.presentation.components.DetailsActionButton
import uz.droid.wallatopia.presentation.components.ImageDetailsTopBar
import uz.droid.wallatopia.presentation.components.advancedShadow
import uz.droid.wallatopia.presentation.screens.contracts.ImageDetailsContract
import uz.droid.wallatopia.presentation.viewmodels.ImageDetailsViewModel
import uz.droid.wallatopia.rememberShareManager
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.download
import wallatopia.composeapp.generated.resources.favorites_title
import wallatopia.composeapp.generated.resources.set
import wallatopia.composeapp.generated.resources.share

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ImageDetailsScreen(
    imageUiModel: ImageUiModel,
    onBackPressed: () -> Unit = {}
) {
    val viewModel: ImageDetailsViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val event = viewModel::onEventDispatch
    val statusBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)
    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues(LocalDensity.current)
    val sharedTransitionScope = LocalSharedTransition.current!!
    val animatedVisibilityScope = LocalAnimatedVisibility.current!!
    val scaffoldState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()
    val shareManager = rememberShareManager()

    LaunchedEffect(Unit) {
        event(ImageDetailsContract.Intent.Init(imageUiModel = imageUiModel))
    }

    val painterBlur = rememberAsyncImagePainter(
        model = ImageRequest.Builder(
            LocalPlatformContext.current
        ).data(uiState.imageUiModel.thumbUrl)
            .crossfade(true)
            .placeholderMemoryCacheKey(imageUiModel.thumbUrl)
            .memoryCacheKey(imageUiModel.thumbUrl)
            .diskCacheKey(imageUiModel.thumbUrl)
            .build(),
        contentScale = ContentScale.Crop
    )
    ModalBottomSheetLayout(
        sheetState = scaffoldState,
        sheetElevation = 0.dp,
        sheetBackgroundColor = AppTheme.colorScheme.eerieBlack,
        sheetContent = {
            SetWallpaperSheetContent(
                imageOriginalUrl = uiState.imageUiModel.originalUrl,
                onClose = {
                    scope.launch {
                        scaffoldState.hide()
                    }
                }
            )
        },
        sheetShape = AppTheme.shape.rounded15
    ) {
        with(sharedTransitionScope) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterBlur,
                    contentDescription = "Wallpaper",
                    modifier = Modifier.fillMaxSize().blur(radius = 70.dp),
                    contentScale = ContentScale.Crop
                )
                if (isVersionBelow12) {
                    Box(Modifier.fillMaxSize().background(AppTheme.colorScheme.cyanBlue.copy(.5f)))
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ImageDetailsTopBar(
                        modifier = Modifier
                            .padding(
                                top = statusBarsPadding.calculateTopPadding() + 8.dp,
                                start = 10.dp,
                                end = 10.dp
                            ),
                        onBackPressed = onBackPressed
                    )

                    SubcomposeAsyncImage(
                        modifier = Modifier.weight(1f)
                            .padding(vertical = 24.dp)
                            .padding(horizontal = 40.dp)
                            .advancedShadow(
                                blur = 13.dp
                            )
                            .sharedElement(
                                state = rememberSharedContentState({ imageUiModel.thumbUrl }),
                                animatedVisibilityScope = animatedVisibilityScope,
                                clipInOverlayDuringTransition = OverlayClip(AppTheme.shape.rounded15),
                            )
                            .clip(AppTheme.shape.rounded15),
                        model = ImageRequest.Builder(
                            LocalPlatformContext.current
                        )
                            .data(uiState.imageUiModel.originalUrl)
                            .crossfade(true)
                            .placeholderMemoryCacheKey(imageUiModel.thumbUrl)
                            .build(),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop
                    )

                    Row(
                        modifier = Modifier
                            .padding(bottom = navigationBarsPadding.calculateBottomPadding() + 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        DetailsActionButton(
                            titleRes = Res.string.share,
                            iconRes = Drawables.Icons.Share,
                            onClick = {
                                shareManager.share(uiState.imageUiModel.originalUrl)
                            }
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            IconButton(onClick = {
                                scope.launch {
                                    scaffoldState.show()
                                }
                            }) {
                                Icon(
                                    painter = painterResource(if (isAndroid) Drawables.Icons.SetWallpaper else Drawables.Icons.Download),
                                    contentDescription = "Share download icon",
                                    modifier = Modifier
                                        .size(56.dp)
                                        .clip(CircleShape)
                                        .background(
                                            color = AppTheme.colorScheme.immutableWhite,
                                            shape = CircleShape
                                        )
                                        .padding(14.dp),
                                    tint = AppTheme.colorScheme.immutableDark
                                )
                            }
                            Text(
                                text = stringResource(if (isAndroid) Res.string.set else Res.string.download),
                                style = AppTheme.typography.buttonTextMedium,
                                color = AppTheme.colorScheme.immutableWhite,
                                modifier = Modifier
                                    .background(
                                        color = AppTheme.colorScheme.charcoalBlue.copy(
                                            alpha = 0.53f
                                        ),
                                        shape = AppTheme.shape.rounded10
                                    )
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }

                        DetailsActionButton(
                            titleRes = Res.string.favorites_title,
                            iconRes = if (uiState.imageUiModel.isFavorite) Drawables.Icons.FavouriteSelected else Drawables.Icons.FavoriteOutlined,
                            tint = if (uiState.imageUiModel.isFavorite) AppTheme.colorScheme.neonFuchsiaPink else AppTheme.colorScheme.immutableWhite,
                            sharedAnimationModifier = Modifier.sharedElement(
                                state = rememberSharedContentState("favorite-bounds-${imageUiModel.thumbUrl}"),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                            onClick = {
                                event(ImageDetailsContract.Intent.HandleInFavorites)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SetWallpaperSheetContent(
    modifier: Modifier = Modifier,
    imageOriginalUrl: String,
    onClose: () -> Unit
) {
    ImageDetailsBottomSheetContent(
        modifier = modifier,
        imageOriginalUrl = imageOriginalUrl,
        onClose = onClose
    )
}

@Composable
fun SetScreenTypeItem(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    title: StringResource,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .clickable(onClick = onClick)
            .padding(start = 50.dp, top = 26.dp, bottom = 15.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "home screen"
        )
        Text(
            text = stringResource(title),
            style = AppTheme.typography.sheetItemTitle
                .copy(AppTheme.colorScheme.immutableWhite)
        )
    }
}

@Composable
fun SetScreenTypeItemDivider() {
    Divider(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 26.dp, end = 24.dp),
        color = AppTheme.colorScheme.darkCharcoal,
        thickness = 1.dp
    )
}