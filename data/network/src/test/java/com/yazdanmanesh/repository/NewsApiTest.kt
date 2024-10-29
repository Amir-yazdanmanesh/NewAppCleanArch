package com.yazdanmanesh.repository

import com.yazdanmanesh.network.dto.getNewsDto
import com.yazdanmanesh.network.service.NewsApi
import com.yazdanmanesh.network.utils.Constants.API_KEY
import com.yazdanmanesh.network.utils.Constants.GET_EVERYTHING
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.enqueueResponse
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GithubApiTest {

    private val mockWebService = MockWebServer()
    private val num = 3
    private val page = 1

    private val client = OkHttpClient.Builder()
        .connectTimeout(8, TimeUnit.SECONDS)
        .readTimeout(8, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebService.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(NewsApi::class.java)

    @After
    fun tearDown() {
        mockWebService.shutdown()
    }

    @Test
    fun `Given 200 response When fetching news Then returns news correctly`() {
        // Given
        mockWebService.enqueueResponse(
            fileName = "news.json",
            code = 200
        )
        val expected = getNewsDto(num)

        // When
        val actual = runBlocking { api.getTehranArticles(num = num, page = page) }
        val request = mockWebService.takeRequest()

        // Then
        assertEquals(expected, actual)
        assertEquals(
            "/$GET_EVERYTHING?q=Tehran&page=$page&pageSize=$num&apiKey=$API_KEY",
            request.path
        )
    }

    @Test
    fun `Given 401 When fetching news Then throws HttpException`() {
        mockWebService.enqueueResponse(
            fileName = "unauthorized.json",
            code = 401
        )
        val exception = assertFailsWith<HttpException> {
            runBlocking { api.getTehranArticles(num = num, page = page, apiKey = "WrongKey") }
        }
        assertEquals(401, exception.code())
        assertEquals(
            "/$GET_EVERYTHING?q=Tehran&page=$page&pageSize=$num&apiKey=WrongKey",
            mockWebService.takeRequest().path
        )
    }
}
