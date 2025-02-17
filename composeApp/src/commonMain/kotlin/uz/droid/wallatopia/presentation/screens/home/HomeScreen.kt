package uz.droid.wallatopia.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.paging.compose.collectAsLazyPagingItems
import kotlinx.serialization.Contextual
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.domain.model.CategoryUiModel
import uz.droid.wallatopia.domain.model.ImageUiModel
import uz.droid.wallatopia.presentation.components.BaseBackground
import uz.droid.wallatopia.presentation.components.CategoryItem
import uz.droid.wallatopia.presentation.components.HomeCustomTab
import uz.droid.wallatopia.presentation.components.MainImageItem
import uz.droid.wallatopia.presentation.components.HomeSearchSection
import uz.droid.wallatopia.presentation.components.advancedShadow
import uz.droid.wallatopia.presentation.navigation.BottomScreens
import uz.droid.wallatopia.presentation.screens.contracts.HomeContract
import uz.droid.wallatopia.presentation.viewmodels.HomeViewModel
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.ai_generated
import wallatopia.composeapp.generated.resources.generate_with_ai
import wallatopia.composeapp.generated.resources.let_ai_paint_your_screen
import wallatopia.composeapp.generated.resources.popular_categories
import wallatopia.composeapp.generated.resources.see_more
import wallatopia.composeapp.generated.resources.trending

@Composable
fun HomeScreen(
    navigateToSearch: () -> Unit,
    navigateToCategories: () -> Unit,
    navigateToImageGenerate: () -> Unit,
    navigateToImageDetails: (ImageUiModel) -> Unit,
) {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val event = viewModel::onEventDispatch
    val pagingItems = uiState.homeImages.collectAsLazyPagingItems()
    val systemBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)
    BaseBackground(backgroundImage = Drawables.Images.HomeBackground) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier.fillMaxSize().padding(start = 32.dp, end = 36.dp),
            contentPadding = PaddingValues(
                top = systemBarsPadding.calculateTopPadding() + 17.dp,
                bottom = 100.dp,
            ),
            verticalItemSpacing = 18.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                HomeSearchSection(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = navigateToSearch
                )
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                AIGenerateCardSection(
                    modifier = Modifier.height(136.dp),
                    onClick = navigateToImageGenerate
                )
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                PopularCategoriesTitleSection(
                    onSeeMore = {
                        navigateToCategories()
                    }
                )
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                PopularCategoriesListSection(
                    categories = uiState.categories
                )
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                HomeTabSection(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    tabs = tabs,
                    selectedIndex = uiState.selectedTabIndex,
                    selectedTab = {
                        event(HomeContract.Intent.SelectTab(it))
                    }
                )
            }

            items(if (uiState.selectedTabIndex == 0) pagingItems.itemCount else uiState.aiGeneratedImages.size,
                key = { it }
            ) { index ->
                val image = if (uiState.selectedTabIndex == 0) pagingItems[index]
                    ?: return@items else uiState.aiGeneratedImages.ifEmpty { return@items }[index]
                MainImageItem(
                    image = image,
                    onClick = {
                        navigateToImageDetails(image)
                    },
                    onFavoriteClick = {
                        if (it) {
                            event(HomeContract.Intent.DeleteFromFavorites(image))
                        } else {
                            event(HomeContract.Intent.AddToFavorites(image))
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AIGenerateCardSection(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = Modifier.advancedShadow(
            offsetY = 4.dp,
            blur = 4.dp
        )
    ) {
        Image(
            modifier = Modifier.then(modifier).fillMaxWidth().height(136.dp)
                .clip(AppTheme.shape.rounded7).clickable(
                    onClick = onClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple()
                ),
            painter = painterResource(Drawables.Images.HomeBanner),
            contentDescription = "banner",
            contentScale = ContentScale.Crop
        )

        Text(
            text = stringResource(Res.string.let_ai_paint_your_screen),
            color = AppTheme.colorScheme.immutableWhite,
            textAlign = TextAlign.End,
            style = AppTheme.typography.sectionHeadline,
            modifier = Modifier.align(Alignment.TopEnd).padding(top = 9.dp, end = 18.dp)
        )

        Button(
            onClick = onClick,
            contentPadding = PaddingValues(14.dp, 10.dp),
            shape = AppTheme.shape.rounded4,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppTheme.colorScheme.purpleA800F7,
                contentColor = AppTheme.colorScheme.immutableWhite
            ),
            modifier = Modifier.align(Alignment.BottomEnd).padding(
                bottom = 27.dp, end = 18.dp
            )
        ) {
            Text(
                text = stringResource(Res.string.generate_with_ai),
                style = AppTheme.typography.buttonTextSmall
            )
        }
    }
}

@Composable
fun PopularCategoriesTitleSection(modifier: Modifier = Modifier, onSeeMore: () -> Unit) {
    Row(
        modifier = Modifier.then(modifier).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.popular_categories),
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
            modifier = Modifier.clip(AppTheme.shape.rounded4).clickable(
                onClick = onSeeMore,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            ).padding(start = 10.dp, top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.see_more),
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
    modifier: Modifier = Modifier, categories: List<CategoryUiModel> = emptyList()
) {
    Row(
        modifier = Modifier.then(modifier).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        categories.take(4).forEach {
            CategoryItem(
                Modifier.advancedShadow(
                    offsetY = 4.dp, blur = 4.dp
                ).height(48.dp).clip(AppTheme.shape.rounded4).fillMaxWidth().weight(1f),
                category = it
            )
        }
    }
}

@Composable
fun HomeTabSection(
    modifier: Modifier = Modifier,
    tabs: List<TabItem>,
    selectedIndex: Int = 0,
    selectedTab: (Int) -> Unit
) {
    HomeCustomTab(
        modifier = modifier,
        selectedItemIndex = selectedIndex,
        onClick = {
            selectedTab(it)
        },
        items = tabs
    )
}

data class TabItem(
    val image: DrawableResource, @Contextual val tabTitle: StringResource
)


val tabs = listOf(
    TabItem(Drawables.Icons.Trending, Res.string.trending),
    TabItem(Drawables.Icons.AiGenerate, Res.string.ai_generated),
)