package uz.droid.wallatopia.presentation.screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SplashScreen(
    navigateToHome: () -> Unit,
) {
    Button(
        onClick = navigateToHome
    ) {
        Text("Go to home")
    }
}