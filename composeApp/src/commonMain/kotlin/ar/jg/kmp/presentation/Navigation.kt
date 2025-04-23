package ar.jg.kmp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.jg.kmp.presentation.conversion.ConversionScreen
import ar.jg.kmp.presentation.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onConvertClick = { navController.navigate("conversion") })
        }
        composable("conversion") {
            ConversionScreen(onBack = { navController.popBackStack() })
        }
    }
}