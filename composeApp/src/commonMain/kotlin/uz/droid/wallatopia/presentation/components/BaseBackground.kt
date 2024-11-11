package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BaseBackground(
    modifier: Modifier = Modifier,
    backgroundImage: DrawableResource,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier.then(modifier)) {
        Image(
            painter = painterResource(backgroundImage),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        content()
    }
}