@file:OptIn(KoinExperimentalAPI::class)

package uz.droid.wallatopia

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import uz.droid.wallatopia.presentation.viewModel.TestViewModel

@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            val vm: TestViewModel = koinViewModel()
            val images by vm.state.collectAsStateWithLifecycle()

            if (images.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
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
                                model = image.urls.regular,
                                contentDescription = "image.description"
                            )
                        }
                    }
                }
            }
        }
    }
}