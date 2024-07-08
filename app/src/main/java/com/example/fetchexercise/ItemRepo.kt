package com.example.fetchexercise

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Repository class to handle data operations related to Items
class ItemRepo {
    private val api: Api

    // Initialization block to set up Retrofit and create the Api instance
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/") // Base URL for the API
            .addConverterFactory(GsonConverterFactory.create()) // Converter to parse JSON
            .build()

        api = retrofit.create(Api::class.java) // Create the Api implementation
    }

    // Suspend function to fetch items from the API
    suspend fun fetchItems() : List<Item> {
        val response = api.getItems() // Perform the network request

        // Check if the response is successful
        if(response.isSuccessful){
            // Filter out items with null or blank names, and sort by listId and name
            return response.body()?.filter { !it.name.isNullOrBlank() }?.sortedWith(
                compareBy<Item> { it.listId }.thenBy { it.name }
            ) ?: emptyList()
        }
        // Return an empty list if the response is not successful
        return emptyList()
    }
}