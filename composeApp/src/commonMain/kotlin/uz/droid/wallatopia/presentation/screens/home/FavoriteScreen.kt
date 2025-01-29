package uz.droid.wallatopia.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.presentation.components.BaseBackground
import uz.droid.wallatopia.presentation.components.MainImageItem
import uz.droid.wallatopia.presentation.screens.contracts.FavoritesContract
import uz.droid.wallatopia.presentation.screens.contracts.HomeContract
import uz.droid.wallatopia.presentation.viewmodels.FavoritesViewModel
import uz.droid.wallatopia.presentation.viewmodels.HomeViewModel
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.favorites_title

@Composable
fun FavoriteScreen() {
    val viewModel: FavoritesViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val event = viewModel::onEventDispatch
    val systemBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)

    BaseBackground(
        modifier = Modifier.fillMaxSize(),
        backgroundImage = Drawables.Images.SearchBackground,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = systemBarsPadding.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.favorites_title),
                style = AppTheme.typography.pageHeadline,
                color = AppTheme.colorScheme.immutableWhite,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(vertical = 10.dp)
            )
            if (uiState.favoriteImages.isNotEmpty()) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize().padding(start = 32.dp, end = 36.dp),
                    contentPadding = PaddingValues(
                        bottom = 100.dp,
                        top = 14.dp
                    ),
                    verticalItemSpacing = 18.dp,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(uiState.favoriteImages, key = { it.id }) { image ->
                        MainImageItem(
                            image = image,
                            onClick = {

                            },
                            onFavoriteClick = {
                                event(FavoritesContract.Intent.DeleteFromFavorites(image))
                            }
                        )
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "No favorites",
                        modifier = Modifier.align(Alignment.Center),
                        style = AppTheme.typography.labelLarge,
                        color = AppTheme.colorScheme.silverGray
                    )
                }
            }
        }

    }
}