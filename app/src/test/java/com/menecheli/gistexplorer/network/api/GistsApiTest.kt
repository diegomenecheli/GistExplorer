package com.menecheli.gistexplorer.network.api

import com.menecheli.gistexplorer.utils.Constants.BASE_URL
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GistsApiTest {
    private lateinit var server: MockWebServer
    private lateinit var api: GistsApi
    private var client = OkHttpClient.Builder().build()

    @Before
    fun setup() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GistsApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `fetchAllGists, returns not null`() = runBlocking {
        val data = api.fetchAllGists()

        assertNotNull(data.body());
    }

    @Test
    fun `getGist, returns the right gist`() = runBlocking {
        val data = api.getGist("57e58de37a583616ca4b822e8ab49e45")

        assertNotNull(data.body());
        assertEquals(data.body()!!.id, "57e58de37a583616ca4b822e8ab49e45")
        assertEquals(
            data.body()!!.url,
            "https://gist.github.com/gtasnail/57e58de37a583616ca4b822e8ab49e45"
        )
        assertEquals(data.body()!!.owner.login, "gtasnail")
    }
}