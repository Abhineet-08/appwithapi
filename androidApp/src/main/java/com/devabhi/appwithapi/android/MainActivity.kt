package com.devabhi.appwithapi.android

import com.devabhi.appwithapi.Breach
import com.devabhi.appwithapi.DataFetcher
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    private val dataFetcher = DataFetcher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BreachListScreen(dataFetcher)

        }
    }
}


@Composable
fun BreachListScreen(dataFetcher: DataFetcher) {
    var breaches by remember { mutableStateOf<List<Breach>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            breaches = dataFetcher.fetchBreaches() // Fetch the breaches
            isLoading = false
        } catch (e: Exception) {
            errorMessage = "Error fetching breaches: ${e.message}"
            isLoading = false
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator() // Show loading spinner
        }
    } else if (errorMessage.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = errorMessage) // Display error message
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(breaches) { breach ->
                BreachItem(breach) // Display each breach in the list
            }
        }
    }
}

@Composable
fun BreachItem(breach: Breach) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Name: ${breach.name}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Domain: ${breach.domain}", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "Description: ${breach.description}",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
