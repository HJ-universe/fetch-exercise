package com.example.fetchexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchexercise.ui.theme.FetchExerciseTheme

// Main activity of the application
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge display
        setContent {
            FetchExerciseTheme {
                // Define the UI within the theme
                Surface() {
                    ItemListScreen() // Display the item list screen
                }
            }
        }
    }
}

// Composable function to display the item list screen
@Composable
fun ItemListScreen(viewModel: ItemViewModel = viewModel()) {
    // Observe the filtered items from the ViewModel
    val items by remember { mutableStateOf(viewModel.filteredItems) }
    // Get distinct list IDs from the items
    val listIds = viewModel.items.map { it.listId }.distinct()

    // Layout column with top padding
    Column(modifier = Modifier.padding(top = 16.dp)) {
        // Dropdown menu to select a List ID
        ListIdDropdown(
            listIds = listIds,
            selectedListId = viewModel.selectedListId,
            onListIdSelected = { viewModel.selectListId(it) }
        )

        // LazyColumn to display the list of items
        LazyColumn {
            // Display each item in the list
            items(items) { item ->
                ItemRow(item) // Display item row
                Divider() // Divider between items
            }
        }
    }
}

// Composable function to display a dropdown menu for selecting List IDs
@Composable
fun ListIdDropdown(
    listIds: List<Int>,
    selectedListId: Int?,
    onListIdSelected: (Int?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) } // State to control dropdown expansion
    var selectedText by remember { mutableStateOf("Select List ID") } // State to store selected text

    // Box layout with padding
    Box(modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp)) {
        Column {
            // Button to trigger dropdown menu
            TextButton(
                onClick = { expanded = !expanded },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = selectedText)
            }
            // Dropdown menu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
            ) {
                // Menu item to select all items
                DropdownMenuItem(onClick = {
                    onListIdSelected(null)
                    selectedText = "All"
                    expanded = false
                }) {
                    Text(text = "All")
                }
                // Menu items for each List ID
                listIds.forEach { listId ->
                    DropdownMenuItem(onClick = {
                        onListIdSelected(listId)
                        selectedText = "List ID: $listId"
                        expanded = false
                    }) {
                        Text(text = "List ID: $listId")
                    }
                }
            }
        }
    }
}

// Composable function to display an individual item row
@Composable
fun ItemRow(item: Item) {
    // Card layout with padding and elevation
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        // Column layout inside the card
        Column(modifier = Modifier.padding(16.dp)) {
            // Display item name
            Text(text = "Name : ${item.name ?: "No Name"}", style = MaterialTheme.typography.h6)
            // Display List ID
            Text(text = "List ID : \t${item.listId}", style = MaterialTheme.typography.body1)
        }
    }
}
