@file:OptIn(KoinExperimentalAPI::class)

package uz.droid.wallatopia.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Text
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.domain.model.CategoryUiModel
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.presentation.components.BaseBackground
import uz.droid.wallatopia.presentation.components.CategoryItem
import uz.droid.wallatopia.presentation.components.MainImageItem
import uz.droid.wallatopia.presentation.components.SearchTopBar
import uz.droid.wallatopia.presentation.components.advancedShadow
import uz.droid.wallatopia.presentation.screens.contracts.SearchContract
import uz.droid.wallatopia.presentation.viewmodels.SearchViewModel
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.browse_by_category
import wallatopia.composeapp.generated.resources.search_results_for

@Composable
fun SearchScreen(
    navigateToCategoryDetails: (categoryId: String) -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel: SearchViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val event = viewModel::onEventDispatch
    val systemBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)

    BaseBackground(backgroundImage = Drawables.Images.SearchBackground) {
        Column(
            modifier = Modifier
                .padding(top = systemBarsPadding.calculateTopPadding())
        ) {
            SearchTopBar(
                query = uiState.query,
                suggestions = uiState.suggestions,
                modifier = Modifier
                    .padding(start = 20.dp, top = 26.dp, end = 31.dp),
                onBack = {
                    if (uiState.searchResults.isEmpty())
                        onBackPressed()
                    else
                        event(SearchContract.Intent.ClearSearch)
                },
                onValueChange = {
                    event(SearchContract.Intent.OnQueryChange(it))
                },
                onSearch = {
                    event(SearchContract.Intent.Search)
                }
            )

            if (uiState.searchResults.isEmpty()) {
                BrowseByCategorySection(
                    categories = uiState.categories,
                    navigateToCategoryDetails = navigateToCategoryDetails
                )
            } else {
                SearchResultsSection(
                    searchedQuery = uiState.lastQuery,
                    searchResults = uiState.searchResults,
                    onAddToFavorite = { image ->
                        event(SearchContract.Intent.AddToFavorite(image))
                    }
                )
            }
        }
    }
}

@Composable
fun BrowseByCategorySection(
    categories: List<CategoryUiModel>,
    navigateToCategoryDetails: (categoryId: String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 41.dp, end = 31.dp, top = 50.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = stringResource(Res.string.browse_by_category),
            style = AppTheme.typography.listTitle,
            color = AppTheme.colorScheme.immutableWhite
        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(categories) {
                CategoryItem(
                    Modifier
                        .height(64.dp)
                        .advancedShadow(
                            offsetY = 5.dp,
                            blur = 5.dp
                        )
                        .fillMaxWidth().clip(AppTheme.shape.rounded5)
                        .clickable(
                            onClick = {
                                navigateToCategoryDetails(it.id)
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple()
                        ),
                    category = it,
                    titleStyle = AppTheme.typography.buttonTextSecondary
                )
            }
        }
    }
}

@Composable
fun SearchResultsSection(
    searchedQuery: String,
    searchResults: List<ImageUiModel>,
    onAddToFavorite: (ImageUiModel) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 35.dp, end = 37.dp, top = 17.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(Res.string.search_results_for),
                style = AppTheme.typography.inputText,
                color = AppTheme.colorScheme.immutableWhite
            )
            Text(
                searchedQuery,
                style = AppTheme.typography.listTitle,
                color = AppTheme.colorScheme.immutableWhite
            )
        }

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier.fillMaxSize().padding(start = 2.dp),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(
                bottom = 100.dp,
            )
        ) {
            items(searchResults) { image ->
                MainImageItem(
                    image = image,
                    onClick = {

                    },
                    onFavoriteClick = {
                        onAddToFavorite(image)
                    }
                )
            }
        }
    }
}