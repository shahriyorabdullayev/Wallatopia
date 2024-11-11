package uz.droid.wallatopia.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.koinInject
import uz.droid.wallatopia.Screens
import uz.droid.wallatopia.presentation.viewmodels.FavoritesViewModel
import uz.droid.wallatopia.presentation.viewmodels.TestViewModel

@Composable
fun FavoriteScreen() {
    val vm: FavoritesViewModel = koinInject()
    val images by vm.state.collectAsStateWithLifecycle()
    if (images.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No images")
        }
    } else {
        AnimatedVisibility(
            visible = images.isNotEmpty()
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2)
            ) {
                items(images) { image ->
                    AsyncImage(
                        model = image.url,
                        contentDescription = "image.description",
                        modifier = Modifier
                            .clickable {
                                vm.deleteImageFromFavorites(image)
                            }
                    )
                }
            }
        }
    }
}