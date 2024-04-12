package com.menecheli.gistexplorer.ui.components.lists


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.menecheli.gistexplorer.presenter.gist.GistPresenter
import com.menecheli.gistexplorer.ui.components.EndlessLazyColumn
import com.menecheli.gistexplorer.ui.components.itens.GistItem
import kotlinx.coroutines.launch

@Composable
fun GistList(navController: NavController, gistPresenter: GistPresenter) {
    val scope = rememberCoroutineScope()
    Log.d("Diego", "GistList: ${gistPresenter.gists.toList()}")

    EndlessLazyColumn(
        items = gistPresenter.gists,
        itemContent = { gist ->
            GistItem(
                navigation = navController,
                gist = gist
            ) {
                gistPresenter.toggleFavoriteStatus(gist.id)
            }
        },
        loadMore = {
            scope.launch {
                gistPresenter.fetchGists()
            }
        }
    )
}
