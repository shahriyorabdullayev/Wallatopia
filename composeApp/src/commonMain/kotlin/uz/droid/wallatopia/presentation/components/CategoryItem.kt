package uz.droid.wallatopia.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.domain.model.CategoryUiModel

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: CategoryUiModel,
    titleStyle: TextStyle = AppTheme.typography.listItemTitle
) {

    Box(
        modifier = Modifier
            .then(modifier)
            .wrapContentWidth(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = category.coverPhotoUrl,
            contentDescription = category.name,
            contentScale = ContentScale.Crop
        )

        Box(
            Modifier.fillMaxSize().background(AppTheme.colorScheme.immutableDark.copy(.59f))
        )
        Text(
            text = category.name,
            color = AppTheme.colorScheme.gainsBoroGray,
            style = titleStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}