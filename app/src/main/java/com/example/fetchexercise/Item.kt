package com.example.fetchexercise

import com.google.gson.annotations.SerializedName

// Data class representing an Item
data class Item(
    @SerializedName("id") val id: Int,        // Unique identifier for the item
    @SerializedName("listId") val listId: Int, // ID of the list to which the item belongs
    @SerializedName("name") val name: String?  // Name of the item, can be null
)
