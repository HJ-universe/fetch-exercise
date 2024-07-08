package com.example.fetchexercise

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// ViewModel class to manage the state and business logic for the item list
class ItemViewModel : ViewModel() {
    // Repository instance to fetch items
    private val repository = ItemRepo()

    // List to hold all items
    var items = mutableStateListOf<Item>()
        private set

    // List to hold filtered items based on selected List ID
    var filteredItems = mutableStateListOf<Item>()
        private set

    // Variable to hold the currently selected List ID
    var selectedListId by mutableStateOf<Int?>(null)
        private set

    // Initialization block to fetch items from the repository
    init {
        viewModelScope.launch {
            items.addAll(repository.fetchItems()) // Fetch items and add to the items list
            filteredItems.addAll(items) // Initially, all items are displayed
        }
    }

    // Function to select a List ID and filter items based on it
    fun selectListId(listId: Int?) {
        selectedListId = listId // Set the selected List ID
        filterItems() // Filter items based on the selected List ID
    }

    // Function to filter items based on the selected List ID
    private fun filterItems() {
        filteredItems.clear() // Clear the current filtered items
        filteredItems.addAll(
            if (selectedListId == null) items else items.filter { it.listId == selectedListId }
            // If no List ID is selected, display all items. Otherwise, filter by the selected List ID
        )
    }
}