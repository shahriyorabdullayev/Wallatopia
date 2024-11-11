package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import uz.droid.wallatopia.common.resources.Drawables
import uz.droid.wallatopia.common.theme.AppTheme

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryName: String = "Abstract",
    contentPaddingValues: PaddingValues
) {
    Card(
        shape = AppTheme.shape.rounded4,
        modifier = Modifier.then(modifier)
    ) {
        Box(
            modifier = Modifier.wrapContentWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(Drawables.Images.TestBackground),
                contentDescription = categoryName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                Modifier.fillMaxSize().background(AppTheme.colorScheme.immutableDark.copy(.59f))
            )
            Text(
                categoryName,
                color = AppTheme.colorScheme.gainsBoroGray,
                modifier = Modifier.padding(paddingValues = contentPaddingValues),
                style = AppTheme.typography.listItemTitle
            )
        }
    }
}