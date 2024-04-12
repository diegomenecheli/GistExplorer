package com.menecheli.gistexplorer.domain.gist.data

import com.menecheli.gistexplorer.domain.gist.db.GistsDataBase
import com.menecheli.gistexplorer.domain.gist.model.Gist

class GistsRepository(private val db: GistsDataBase) {
    suspend fun getGist(gistId: String): Gist = db.getGistsDao().getGist(gistId)

    suspend fun getAllGists(): List<Gist> = db.getGistsDao().getAllGists()

    suspend fun saveAllGists(gistList: List<Gist>) =
        db.getGistsDao().saveAllGists(gistList)

    suspend fun getAllFavorites(): List<Gist> = db.getGistsDao().getAllFavorites()

    suspend fun toggleFavoriteStatus(id: String) {
        db.getGistsDao().toggleFavoriteStatus(id)
    }
}