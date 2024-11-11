package uz.droid.wallatopia.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.presentation.screens.home.TabItem

@Composable
fun HomeTabItem(
    modifier: Modifier = Modifier,
    tabItem: TabItem,
    isActive: Boolean,
    activeContainerColor: Color,
    inActiveContainerColor: Color,
    activeContentColor: Color,
    inActiveContentColor: Color,
    onTabSelected: () -> Unit,
) {

    val containerColor: Color by animateColorAsState(
        targetValue = if (isActive) {
            activeContainerColor
        } else {
            inActiveContainerColor
        },
        animationSpec = tween(easing = LinearEasing, durationMillis = 150),
        label = "tab_container_color_animation",
    )

    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(AppTheme.shape.rounded6)
            .background(containerColor)
            .clickable {
                onTabSelected()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.then(modifier),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Image(
                painter = painterResource(tabItem.image), contentDescription = tabItem.tabTitle,
                colorFilter = ColorFilter.tint(color = if (isActive) activeContentColor else inActiveContentColor)
            )
            Text(
                tabItem.tabTitle,
                style = AppTheme.typography.labelMedium.copy(letterSpacing = if (isActive) 1.sp else 0.sp),
                color = if (isActive) activeContentColor else inActiveContentColor
            )
        }
    }
}

@Composable
fun HomeCustomTab(
    selectedItemIndex: Int,
    items: List<TabItem>,
    modifier: Modifier = Modifier,
    onClick: (index: Int) -> Unit,
) {

    Box(
        modifier = Modifier.then(modifier)
            .clip(AppTheme.shape.rounded6)
            .background(AppTheme.colorScheme.jetGray)
            .height(40.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(AppTheme.shape.rounded6),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.mapIndexed { index, tabItem ->
                val isSelected = index == selectedItemIndex
                HomeTabItem(
                    modifier = Modifier.weight(1f),
                    isActive = isSelected,
                    onTabSelected = {
                        onClick(index)
                    },
                    tabItem = tabItem,
                    activeContainerColor = AppTheme.colorScheme.green00AD9F,
                    inActiveContainerColor = Color.Transparent,
                    activeContentColor = AppTheme.colorScheme.gainsBoroGray,
                    inActiveContentColor = AppTheme.colorScheme.quickSilverGray,
                )
            }
        }
    }
}