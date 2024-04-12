package com.menecheli.gistexplorer.ui

sealed class Screen(val route: String) {
    object MainScreen : Screen("Gists")
    object DetailScreen : Screen("detail_screen")
    object FavoriteScreen : Screen("favorites")
    object WebViewScreen : Screen("web_view_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
