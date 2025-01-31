package uz.droid.wallatopia.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.presentation.components.BaseBackground
import uz.droid.wallatopia.presentation.components.CategoryItem
import uz.droid.wallatopia.presentation.components.CategoryTopBar
import uz.droid.wallatopia.presentation.viewmodels.CategoryViewModel

@Composable
fun CategoryScreen(
    navigateToCategoryDetails: (categoryName:String) -> Unit,
    onBackPressed: () -> Unit
) {

    val viewModel: CategoryViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val systemBarsPadding = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)

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
                modifier = Modifier.fillMaxSize().padding(start = 38.dp, end = 34.dp),
                contentPadding = PaddingValues(
                    top = 19.dp,
                    bottom = 100.dp,
                ),
                verticalItemSpacing = 11.dp,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                items(uiState.categories) {
                    CategoryItem(
                        Modifier.height(90.dp).fillMaxWidth().clip(AppTheme.shape.rounded5)
                            .clickable(
                                onClick = {
                                    navigateToCategoryDetails(it.name)
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
}