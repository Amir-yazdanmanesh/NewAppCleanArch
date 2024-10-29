package com.yazdanmanesh.network.service

import com.yazdanmanesh.network.dto.NewsDto
import com.yazdanmanesh.network.utils.Constants.API_DEFAULT_SEARCH
import com.yazdanmanesh.network.utils.Constants.API_KEY
import com.yazdanmanesh.network.utils.Constants.GET_EVERYTHING
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(GET_EVERYTHING)
    suspend fun getTehranArticles(
        @Query("q") searchQuery: String = API_DEFAULT_SEARCH,
        @Query("page") page: Int,
        @Query("pageSize") num: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsDto
}
