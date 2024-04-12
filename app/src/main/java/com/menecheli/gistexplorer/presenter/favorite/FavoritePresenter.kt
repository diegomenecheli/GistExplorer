package com.menecheli.gistexplorer.presenter.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.menecheli.gistexplorer.domain.gist.data.GistsDataSource
import com.menecheli.gistexplorer.domain.gist.model.Gist

class FavoritePresenter(
    private val dataSource: GistsDataSource
) : FavoriteHome.Presenter {
    var gists = mutableStateListOf<Gist>()
    var isLoading by mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    override fun onComplete() {
        this.dataSource.getAllFavorites(this)
    }

    override fun onError(message: String) {
        errorMessage.value = message
        isLoading = false
    }

    override fun onSuccess(favoriteGists: List<Gist>) {
        errorMessage.value = ""
        gists.clear()
        gists.addAll(favoriteGists)
        isLoading = false
    }

    override fun getFavorites() {
        isLoading = true
        this.dataSource.getAllFavorites(this)
    }

    override fun deleteFavorite(id: String) {
        isLoading = true
        this.dataSource.deleteFavorite(id, this)
    }
}