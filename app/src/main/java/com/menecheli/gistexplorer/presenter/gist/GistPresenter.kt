package com.menecheli.gistexplorer.presenter.gist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.menecheli.gistexplorer.domain.gist.data.GistsDataSource
import com.menecheli.gistexplorer.domain.gist.model.Gist

class GistPresenter(
    private val dataSource: GistsDataSource
) : GistHome.Presenter {
    var gists = mutableStateListOf<Gist>()
    var isLoading by mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    override fun onSuccess(gistsList: List<Gist>) {
        errorMessage.value = ""
        gists.addAll(gistsList)
        isLoading = false
    }

    override fun onError(message: String) {
        errorMessage.value = message
        isLoading = false
    }

    override fun onComplete() {
        errorMessage.value = ""
        this.dataSource.getAllGits(this)
        isLoading = false
    }

    override fun getAllGists() {
        isLoading = true
        this.dataSource.getAllGits(this)
    }

    override fun fetchGists() {
        isLoading = true
        this.dataSource.fetchGists(this)
        this.dataSource.getAllGits(this)
    }

    override fun toggleFavoriteStatus(id: String) {
        isLoading = true
        this.dataSource.toggleFavoriteStatus(id, this)
    }
}