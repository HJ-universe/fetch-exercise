## Overview
The application fetches items from the provided URL and displays them in a list based on the following requirements:
- Display all items grouped by "listId".
- Sort the results first by "listId" and then by "name".
- Filter out any items where "name" is blank or null.
- Allow the user to select a List ID from a dropdown menu to filter the displayed items.

## Features
- Fetches data from the given URL using Retrofit.
- Displays items in a list using Jetpack Compose.
- Filters and sorts items based on the requirements.
- Provides a dropdown menu for selecting and filtering items by List ID.

## Project Structure
- MainActivity.kt: Contains the main activity and composable functions for displaying the list and dropdown menu.
- ItemViewModel.kt: Contains the ViewModel that manages the state and logic for the item list.
- ItemRepo.kt: Contains the repository class that handles data fetching using Retrofit.
- Item.kt: Data class representing an item.
- Api.kt: Interface defining the API endpoints using Retrofit.

## Dependencies
- Jetpack Compose
- Retrofit
- Gson
