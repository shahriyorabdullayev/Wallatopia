package uz.droid.wallatopia.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import uz.droid.wallatopia.common.theme.AppTheme
import uz.droid.wallatopia.common.resources.Drawables
import wallatopia.composeapp.generated.resources.Res
import wallatopia.composeapp.generated.resources.app_logo
import wallatopia.composeapp.generated.resources.splash_background

@Composable
fun SplashScreen(
    navigateToHome: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(2000)
        navigateToHome()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1D5967)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Drawables.Images.SplashLogo),
            contentDescription = "App logo",
            modifier = Modifier.width(140.dp).height(110.dp),
        )
        Text(
            text = "Wallatopia",
            style = AppTheme.typography.appNameTitle,
            color = AppTheme.colorScheme.immutableWhite,
            modifier = Modifier.padding(top = 24.dp)
        )
        Spacer(modifier = Modifier.height(36.dp))
        LinearProgressIndicator(
            modifier = Modifier.width(160.dp),
            strokeCap = StrokeCap.Round,
            backgroundColor = AppTheme.colorScheme.spanishGray,
            color = AppTheme.colorScheme.immutableWhite
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "4k Wallpapers",
            style = AppTheme.typography.splashTitle,
            color = AppTheme.colorScheme.immutableWhite,
        )
    }
}



