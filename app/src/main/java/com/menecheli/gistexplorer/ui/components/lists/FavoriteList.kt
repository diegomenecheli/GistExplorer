package com.menecheli.gistexplorer.ui.components.lists

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.menecheli.gistexplorer.presenter.favorite.FavoritePresenter
import com.menecheli.gistexplorer.ui.components.itens.FavoriteItem

@Composable
fun FavoriteList(navController: NavController, favoritePresenter: FavoritePresenter) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyColumn {
            items(
                items = favoritePresenter.gists,
            ) { item ->
                FavoriteItem(item, navController) {
                    favoritePresenter.deleteFavorite(item.id)
                }
            }
        }
    }
}
