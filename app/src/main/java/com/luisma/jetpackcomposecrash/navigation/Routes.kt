package com.luisma.jetpackcomposecrash.navigation

sealed class Routes(val route: String) {
    object MainScreen : Routes("MainScreen")
    object DetailsScreen : Routes("DetailsScreen")

    fun withArg(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }

}