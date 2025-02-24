package uz.droid.wallatopia.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.paging.compose.collectAsLazyPagingItems
import org.koin.compose.viewmodel.koinViewModel
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.presentation.components.BaseBackground
import uz.droid.wallatopia.presentation.components.CategoryTopBar
import uz.droid.wallatopia.presentation.components.MainImageItem
import uz.droid.wallatopia.presentation.screens.contracts.CategoryDetailsContract
import uz.droid.wallatopia.presentation.viewmodels.CategoryDetailsViewModel

@Composable
fun CategoryDetailsScreen(
    categoryName: String,
    onBackPressed: () -> Unit,
    navigateToImageDetails: (ImageUiModel) -> Unit,
) {
    val viewModel: CategoryDetailsViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val event = viewModel::onEventDispatch
    val systemBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)
    val categoryPhotos = uiState.categoryPhotos.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        event(CategoryDetailsContract.Intent.LoadCategoryImages(categoryName))
    }

    BaseBackground(backgroundImage = Drawables.Images.CategoryBackground) {
        Column(
            modifier = Modifier
                .padding(top = systemBarsPadding.calculateTopPadding())
        ) {
            CategoryTopBar(
                modifier = Modifier
                    .padding(start = 29.dp, top = 8.dp)
                    .padding(vertical = 10.dp),
                onBack = onBackPressed
            )

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(3),
                modifier = Modifier.fillMaxSize().padding(horizontal = 38.dp),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    bottom = 100.dp,
                ),
                verticalItemSpacing = 10.dp,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                items(categoryPhotos.itemCount, key = { it }) { index ->
                    val image = categoryPhotos[index] ?: return@items
                    MainImageItem(
                        image = image,
                        onClick = {
                            navigateToImageDetails(image)
                        },
                        onFavoriteClick = {
                            event(CategoryDetailsContract.Intent.AddToFavorite(image))
                        }
                    )
                }
            }
        }
    }
}