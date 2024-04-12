package com.menecheli.gistexplorer.domain.gist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.menecheli.gistexplorer.domain.gist.model.Gist

@Dao
interface GistsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllGists(gistList: List<Gist>)

    @Query("SELECT * FROM gist WHERE id = :gistId")
    suspend fun getGist(gistId: String): Gist

    @Query("SELECT * FROM gist WHERE favorite = 1")
    suspend fun getAllFavorites(): List<Gist>

    @Query("SELECT * FROM gist")
    suspend fun getAllGists(): List<Gist>

    @Query("UPDATE gist SET favorite = 1 - favorite WHERE id = :id")
    suspend fun toggleFavoriteStatus(id: String)
}