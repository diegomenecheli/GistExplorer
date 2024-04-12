package com.menecheli.gistexplorer.presenter.gistDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.menecheli.gistexplorer.domain.gist.data.GistsDataSource
import com.menecheli.gistexplorer.domain.gist.model.Gist

class GistDetailsPresenter(
    private val dataSource: GistsDataSource
) : GistDetailsHome.Presenter {
    var isLoading by mutableStateOf(false)
    var errorMessage = mutableStateOf("")
    var selectedGist by mutableStateOf<Gist?>(null)

    override fun onSuccess(gist: Gist) {
        errorMessage.value = ""
        selectedGist = gist
        isLoading = false
    }

    override fun onError(message: String) {
        errorMessage.value = message
        isLoading = false
    }

    override fun getGist(id: String) {
        isLoading = true
        this.dataSource.getGist(id, this)
    }
}