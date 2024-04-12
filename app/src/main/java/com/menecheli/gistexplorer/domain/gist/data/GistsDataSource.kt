package com.menecheli.gistexplorer.domain.gist.data

import android.content.Context
import com.menecheli.gistexplorer.domain.gist.db.GistsDataBase
import com.menecheli.gistexplorer.network.RetrofitInstance
import com.menecheli.gistexplorer.presenter.favorite.FavoriteHome
import com.menecheli.gistexplorer.presenter.gist.GistHome
import com.menecheli.gistexplorer.presenter.gistDetails.GistDetailsHome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class GistsDataSource(context: Context) {
    private var db: GistsDataBase = GistsDataBase.getInstance(context)
    private var gistsRepository: GistsRepository = GistsRepository(db)

//    get Gist from the api. not needed.
//    fun getGist(gistId: String, callback: GistHome.Presenter) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = RetrofitInstance.api.getGist(gistId)
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    response.body()?.let { gistResponse ->
//                        callback.onSuccess(gistResponse)
//                    }
//                } else {
//                    val errorMessage = response.errorBody()?.string()
//                    callback.onError(message = errorMessage ?: "Unknown error")
//                }
//            }
//        }
//    }

    fun getGist(gistId: String, callback: GistDetailsHome.Presenter) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val allGists = gistsRepository.getGist(gistId)
                withContext(Dispatchers.Main) {
                    callback.onSuccess(allGists)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onError(message = e.message ?: "Unknown error")
                }
            }
        }
    }

    fun toggleFavoriteStatus(gistId: String, callback: GistHome.Presenter) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                gistsRepository.toggleFavoriteStatus(gistId)
                withContext(Dispatchers.Main) {
                    callback.onComplete()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onError(message = e.message ?: "Unknown error")
                }
            }
        }
    }

    fun deleteFavorite(gistId: String, callback: FavoriteHome.Presenter) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                gistsRepository.toggleFavoriteStatus(gistId)
                withContext(Dispatchers.Main) {
                    callback.onComplete()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onError(message = e.message ?: "Unknown error")
                }
            }
        }
    }

    fun getAllGits(callback: GistHome.Presenter) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val allGists = gistsRepository.getAllGists()
                withContext(Dispatchers.Main) {
                    callback.onSuccess(allGists)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onError(message = e.message ?: "Unknown error")
                }
            }
        }
    }

    fun getAllFavorites(callback: FavoriteHome.Presenter) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val allGists = gistsRepository.getAllFavorites()
                withContext(Dispatchers.Main) {
                    callback.onSuccess(allGists)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onError(message = e.message ?: "Unknown error")
                }
            }
        }
    }

    fun fetchGists(callback: GistHome.Presenter) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.fetchAllGists()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        response.body()?.let { gistResponse ->
                            gistsRepository.saveAllGists(gistResponse)
                            callback.onSuccess(gistResponse)
                        }
                    } else {
                        val errorMessage = response.errorBody()?.string()
                        callback.onError(message = errorMessage ?: "Unknown error")
                    }
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    callback.onError(message = "Network error: Please check your internet connection")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onError(message = e.message ?: "Unknown error")
                }
            }
        }
    }
}
