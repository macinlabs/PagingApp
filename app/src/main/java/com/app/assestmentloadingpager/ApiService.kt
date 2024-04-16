package com.app.assestmentloadingpager

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getItems(@Query("page") page: Int): Response<List<Item>>
}