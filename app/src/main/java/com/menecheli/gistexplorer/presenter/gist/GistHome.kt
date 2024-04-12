package com.menecheli.gistexplorer.presenter.gist

import com.menecheli.gistexplorer.domain.gist.model.Gist

interface GistHome {
    interface Presenter {
        fun onSuccess(gistsList: List<Gist>)
        fun onError(message: String)
        fun onComplete()
        fun getAllGists()
        fun fetchGists()
        fun toggleFavoriteStatus(id: String)
    }
}