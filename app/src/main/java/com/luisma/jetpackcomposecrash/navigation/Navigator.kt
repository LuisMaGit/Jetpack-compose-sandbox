package com.luisma.jetpackcomposecrash.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luisma.jetpackcomposecrash.ui.demos.DetailsScreen
import com.luisma.jetpackcomposecrash.ui.demos.MainScreen

@Composable
fun Navigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MainScreen.route) {
        //Main Route
        composable(
            Routes.MainScreen.route,
        ) {
            MainScreen(navController = navController)
        }
        //Details Route with fixed arguments
        composable(
            Routes.DetailsScreen.route + "/{text}",
            arguments = listOf(
                navArgument("text") {
                    type = NavType.StringType
                },
            )
        ) { entry ->
            DetailsScreen(text = entry.arguments?.getString("text"))
        }
        //Datils Route with optional arguments
        composable(
            Routes.DetailsScreen.route + "?text={text}",
            arguments = listOf(navArgument("text") {
                type = NavType.StringType
                defaultValue = "These is the defalut value"
            })
        ) {
            DetailsScreen(text = it.arguments?.getString("text"))
        }

    }
}