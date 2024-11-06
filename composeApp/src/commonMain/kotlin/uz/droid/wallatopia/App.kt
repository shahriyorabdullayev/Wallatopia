package uz.droid.wallatopia

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        MainHavHost(navController)
    }
}

@Composable
fun MainHavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen) {

    }
}