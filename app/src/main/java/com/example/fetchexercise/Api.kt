package com.example.fetchexercise

import retrofit2.Response
import retrofit2.http.GET

// Interface defining API endpoints
interface Api {
    // GET request to fetch items from the specified endpoint
    @GET("hiring.json")
    suspend fun getItems(): Response<List<Item>>
}
