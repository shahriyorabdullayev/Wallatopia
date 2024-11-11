@file:OptIn(
    KoinExperimentalAPI::class, ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class
)

package uz.droid.wallatopia.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import uz.droid.wallatopia.App
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.domain.model.UnsplashImage
import uz.droid.wallatopia.presentation.components.BaseBackground
import uz.droid.wallatopia.presentation.components.CategoryItem
import uz.droid.wallatopia.presentation.components.HomeCustomTab
import uz.droid.wallatopia.presentation.components.MainImageItem
import uz.droid.wallatopia.presentation.components.SearchField
import uz.droid.wallatopia.presentation.viewmodels.HomeViewModel

@Composable
fun HomeScreen() {
    val vm: HomeViewModel = koinViewModel()
    val images by vm.state.collectAsStateWithLifecycle()
    val systemBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)

    BaseBackground(backgroundImage = Drawables.Images.HomeBackground) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier.fillMaxSize().padding(start = 32.dp, end = 36.dp),
            contentPadding = PaddingValues(
                top = systemBarsPadding.calculateTopPadding() + 17.dp,
                bottom = 4.dp
            ),
            verticalItemSpacing = 18.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                SearchField(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                AIGenerateCardSection(
                    modifier = Modifier.height(136.dp),
                    onClick = {}
                )
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                PopularCategoriesTitleSection(
                    onSeeMore = {

                    }
                )
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                PopularCategoriesListSection(
                    categories = listOf("Abstract", "Aesthetic", "Nature", "Space")
                )
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                HomeTabSection(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    tabs = tabs
                )
            }

            items(images) { image ->
                MainImageItem(
                    imageUrl = image.urls.regular,
                    onClick = {},
                    onFavoriteClick = {
                        val unsplashImage = UnsplashImage(url = image.urls.regular)
                        vm.addToFavorites(unsplashImage)
                    }
                )
            }
        }
    }
}

@Composable
fun AIGenerateCardSection(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = Modifier.then(modifier).fillMaxWidth().height(136.dp),
        shape = AppTheme.shape.rounded7,
        onClick = onClick
    ) {

    }
}

@Composable
fun PopularCategoriesTitleSection(modifier: Modifier = Modifier, onSeeMore: () -> Unit) {
    Row(
        modifier = Modifier.then(modifier)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Popular Categories",
            color = AppTheme.colorScheme.immutableWhite,
            style = AppTheme.typography.listTitle
        )
        Image(
            painter = painterResource(Drawables.Icons.PopularCategories),
            contentDescription = "categories",
            modifier = Modifier.padding(start = 2.dp)
        )
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier
                .clip(AppTheme.shape.rounded4)
                .clickable(onClick = onSeeMore)
                .padding(start = 10.dp, top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "See More",
                style = AppTheme.typography.labelSmall,
                color = AppTheme.colorScheme.immutableWhite
            )
            Image(
                painter = painterResource(Drawables.Icons.ArrowRight),
                contentDescription = "see more"
            )
        }
    }
}

@Composable
fun PopularCategoriesListSection(
    modifier: Modifier = Modifier,
    categories: List<String> = emptyList()
) {
    Row(
        modifier = Modifier.then(modifier).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        categories.take(4).forEach {
            CategoryItem(
                Modifier.height(48.dp).fillMaxWidth().weight(1f),
                categoryName = it,
                contentPaddingValues = PaddingValues(vertical = 17.dp)
            )
        }
    }
}

@Composable
fun HomeTabSection(
    modifier: Modifier = Modifier,
    tabs: List<TabItem>,
) {
    var selectedIndex by remember { mutableStateOf(0) }

    HomeCustomTab(
        modifier = modifier,
        selectedItemIndex = selectedIndex,
        onClick = {
            selectedIndex = it
        },
        items = tabs
    )
}

data class TabItem(
    val image: DrawableResource,
    val tabTitle: String
)

val tabs = listOf(
    TabItem(Drawables.Icons.Trending, "Trending"),
    TabItem(Drawables.Icons.AiGenerate, "A.I Generated"),
)