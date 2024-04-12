package com.menecheli.gistexplorer.presenter.gistDetails

import com.menecheli.gistexplorer.domain.gist.model.Gist

interface GistDetailsHome {
    interface Presenter {
        fun onSuccess(gist: Gist)
        fun onError(message: String)
        fun getGist(id: String)
    }
}