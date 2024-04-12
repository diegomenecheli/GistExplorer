package com.menecheli.gistexplorer.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.menecheli.gistexplorer.ui.views.TabBarItem
import com.menecheli.gistexplorer.ui.views.TabView
import com.menecheli.gistexplorer.ui.screens.DetailScreen
import com.menecheli.gistexplorer.ui.screens.FavoriteScreen
import com.menecheli.gistexplorer.ui.screens.MainScreen
import com.menecheli.gistexplorer.ui.screens.WebViewScreen
import com.menecheli.gistexplorer.ui.theme.GistExplorerTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    val gistTab = TabBarItem(title = "Gists", selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home)
    val favTab = TabBarItem(title = "Favorites", selectedIcon = Icons.Filled.Favorite, unselectedIcon = Icons.Outlined.FavoriteBorder)

    val tabBarItems = listOf(gistTab, favTab)

    GistExplorerTheme {
        Scaffold(bottomBar = { TabView(tabBarItems, navController) }) {
            NavHost(
                navController = navController,
                startDestination = Screen.MainScreen.route,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                composable(route = Screen.MainScreen.route) {
                    MainScreen(navController, context)
                }
                composable(
                    route = Screen.DetailScreen.route + "/{id}",
                    arguments = listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    ),
                    enterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right
                        )
                    }
                ) { entry ->
                    val id = entry.arguments?.getString("id")
                    DetailScreen(id = id, navController, context)
                }
                composable(
                    route = Screen.WebViewScreen.route + "/{url}",
                    arguments = listOf(
                        navArgument("url") {
                            type = NavType.StringType
                        }
                    )
                ) { entry ->
                    val url = entry.arguments?.getString("url")
                    WebViewScreen(url = url, navController = navController)
                }
                composable(
                    route = Screen.FavoriteScreen.route,

                    ) {
                    FavoriteScreen(navController, context)
                }
            }
        }
    }
}