package com.menecheli.gistexplorer.presenter.favorite

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.menecheli.gistexplorer.domain.gist.data.GistsDataSource
import com.menecheli.gistexplorer.domain.gist.db.GistsDao
import com.menecheli.gistexplorer.domain.gist.db.GistsDataBase
import com.menecheli.gistexplorer.domain.gist.model.Files
import com.menecheli.gistexplorer.domain.gist.model.Gist
import com.menecheli.gistexplorer.domain.gist.model.GistOwner
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class FavoritePresenterTest {
    private lateinit var database: GistsDataBase
    private lateinit var gistsDao: GistsDao
    private lateinit var dataSource: GistsDataSource
    private lateinit var presenter: FavoritePresenter
    private lateinit var firstGist: Gist
    private lateinit var secondGist: Gist
    private lateinit var exampleList: List<Gist>

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GistsDataBase::class.java
        ).allowMainThreadQueries().build()

        gistsDao = database.getGistsDao()
        dataSource = GistsDataSource(context)
        presenter = FavoritePresenter(dataSource)

        firstGist = Gist(
            id = "57e58de37a583616ca4b822e8ab49e45",
            url = "https://gist.github.com/gtasnail/57e58de37a583616ca4b822e8ab49e45",
            description = "All known ped reset config flags | SetPedResetFlag SET_PED_RESET_FLAG |",
            owner = GistOwner(
                login = "gtasnail",
                avatar = "https://avatars.githubusercontent.com/u/100861025?v=4"
            ),
            files = mapOf(
                "pedResetFlags.lua" to Files(
                    name = "pedResetFlags.lua",
                    type = "text/plain",
                    url = "https://gist.githubusercontent.com/gtasnail/57e58de37a583616ca4b822e8ab49e45/raw/e5d7db5af93c21d8cdcb3b1d250d986b2ad54323/pedResetFlags.lua"
                )
            ),
            updated_at = "2024-04-11T22:45:22Z",
            favorite = true
        )
        secondGist = Gist(
            "1f71a7d6a56023e559f0bebd7d83b4cc",
            "https://gist.github.com/numioguri/1f71a7d6a56023e559f0bebd7d83b4cc",
            "",
            GistOwner("numioguri", "https://avatars.githubusercontent.com/u/103483789?v=4"),
            hashMapOf(
                "gistfile1.txt" to Files(
                    "gistfile1.txt",
                    "text/plain",
                    "https://gist.githubusercontent.com/numioguri/1f71a7d6a56023e559f0bebd7d83b4cc/raw/9efacbcb5d89b16c242df6800fa832e27d68df05/gistfile1.txt"
                )
            ),
            "2024-04-11T22:43:52Z",
            true
        )
        exampleList = listOf(firstGist, secondGist)

    }

    @Test
    fun onError() {
        val errorMessage = "Error message"
        presenter.onError(errorMessage)

        assertEquals(errorMessage, presenter.errorMessage.value)
        assertEquals(false, presenter.isLoading)
    }

    @Test
    fun getFavorites() = runBlocking {
        presenter.onSuccess(exampleList)

        assertEquals(false, presenter.isLoading)
        assertEquals(exampleList, presenter.gists)
        assertEquals("", presenter.errorMessage.value)
    }

    @Test
    fun deleteFavorite() = runBlocking {
        val gistIdToDelete = "57e58de37a583616ca4b822e8ab49e45"
        presenter.gists.addAll(exampleList)
        gistsDao.saveAllGists(exampleList)

        presenter.deleteFavorite(gistIdToDelete)
        gistsDao.toggleFavoriteStatus(gistIdToDelete)
        val updatedList = gistsDao.getAllFavorites()
        presenter.onSuccess(updatedList)

        assertNotEquals(exampleList.size, presenter.gists.size)
        assertEquals("", presenter.errorMessage.value)
        assertEquals(false, presenter.isLoading)
    }

    @Test
    fun onSuccess() = runBlocking {
        presenter.onSuccess(exampleList)

        assertEquals(false, presenter.isLoading)
        assertEquals(exampleList, presenter.gists)
        assertEquals("", presenter.errorMessage.value)
    }
}