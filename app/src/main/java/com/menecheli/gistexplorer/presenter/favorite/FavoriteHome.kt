package com.menecheli.gistexplorer.presenter.favorite

import com.menecheli.gistexplorer.domain.gist.model.Gist

interface FavoriteHome {
    interface Presenter {
        fun onComplete()
        fun onError(message: String)
        fun onSuccess(favoriteGists: List<Gist>)
        fun getFavorites()
        fun deleteFavorite(id: String)
    }
}