package com.menecheli.gistexplorer.domain.gist.data

import androidx.test.core.app.ApplicationProvider

import androidx.room.Room
import com.menecheli.gistexplorer.domain.gist.db.GistsDao
import com.menecheli.gistexplorer.domain.gist.db.GistsDataBase
import com.menecheli.gistexplorer.domain.gist.model.Files
import com.menecheli.gistexplorer.domain.gist.model.Gist
import com.menecheli.gistexplorer.domain.gist.model.GistOwner
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GistsRepositoryTest {

    private lateinit var database: GistsDataBase
    private lateinit var gistsDao: GistsDao
    private lateinit var firstGist: Gist
    private lateinit var secondGist: Gist
    private lateinit var exampleList: List<Gist>

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GistsDataBase::class.java
        ).allowMainThreadQueries().build()

        gistsDao = database.getGistsDao()
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
            favorite = false
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
            false
        )
        exampleList = listOf(firstGist, secondGist)
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun getGist() = runBlocking {
        gistsDao.saveAllGists(exampleList)
        val savedGists = gistsDao.getGist("57e58de37a583616ca4b822e8ab49e45")
        assertEquals(firstGist, savedGists)
    }

    @Test
    fun saveAllGists_withSuccess() = runBlocking {
        gistsDao.saveAllGists(exampleList)

        val savedGists = gistsDao.getAllGists()

        assertEquals(exampleList.sortedBy { it.id }, savedGists.sortedBy { it.id })
        assertEquals(exampleList.size, savedGists.size)
    }

    @Test
    fun toggleFavoriteStatus() = runBlocking {
        gistsDao.saveAllGists(exampleList)

        val selectedGist = gistsDao.getGist("57e58de37a583616ca4b822e8ab49e45")
        gistsDao.toggleFavoriteStatus(selectedGist.id)
        val toggledFavorite = gistsDao.getGist(selectedGist.id)

        assertNotEquals(selectedGist.favorite, toggledFavorite.favorite)
    }
}