package com.menecheli.gistexplorer.network.api

import com.menecheli.gistexplorer.domain.gist.model.Gist
import com.menecheli.gistexplorer.domain.gist.model.GistResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GistsApi {
    @GET("gists/public")
    suspend fun fetchAllGists(): Response<GistResponse>

    @GET("gists/{gist_id}")
    suspend fun getGist(
        @Path("gist_id") gist_id: String
    ): Response<Gist>
}