package com.menecheli.gistexplorer.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.menecheli.gistexplorer.domain.gist.data.GistsDataSource
import com.menecheli.gistexplorer.presenter.favorite.FavoritePresenter
import com.menecheli.gistexplorer.ui.views.EmptyView
import com.menecheli.gistexplorer.ui.views.ErrorView
import com.menecheli.gistexplorer.ui.components.lists.FavoriteList
import com.menecheli.gistexplorer.ui.views.LoadingView

@Composable
fun FavoriteScreen(navController: NavController, context: Context) {
    var favoritePresenter: FavoritePresenter? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        val dataSource = GistsDataSource(context)
        favoritePresenter = FavoritePresenter(dataSource)
        favoritePresenter?.getFavorites()
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(48.dp))
            Text(
                text = "Favorites",
                style = MaterialTheme.typography.headlineSmall
            )
        }
        when {
            favoritePresenter == null -> LoadingView()
            favoritePresenter?.isLoading == true -> LoadingView()
            favoritePresenter?.errorMessage!!.value.isNotEmpty() -> ErrorView(errorMessage = favoritePresenter!!.errorMessage.value)
            favoritePresenter?.gists!!.isNotEmpty() -> FavoriteList(
                navController = navController,
                favoritePresenter!!
            )

            else -> EmptyView()
        }
    }
}