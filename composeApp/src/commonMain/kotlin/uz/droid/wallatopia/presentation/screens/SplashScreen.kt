package uz.droid.wallatopia.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uz.droid.wallatopia.common.theme.AppTheme

@Composable
fun SplashScreen(
    navigateToHome: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = navigateToHome
        ) {
            Text(
                "Go to home",
                style = AppTheme.typography.paragraph
            )
        }
    }
}
