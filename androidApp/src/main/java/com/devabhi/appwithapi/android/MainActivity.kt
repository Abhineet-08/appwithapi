package com.devabhi.appwithapi.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.devabhi.appwithapi.ApiClient
import com.devabhi.appwithapi.Data
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val apiClient = ApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                var dataList by remember { mutableStateOf<List<Data>>(emptyList()) }
                val scope = rememberCoroutineScope()

                // Fetch data when the screen appears
                LaunchedEffect(Unit) {
                    scope.launch {
                        dataList = apiClient.fetchData()
                    }
                }

                // Display data in a LazyColumn
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    if (dataList.isEmpty()) {
                        item {
                            Text(
                                text = "No data available, dataList size: ${dataList.size}",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    } else {
                        items(dataList) { data ->
                            DataItem(data)
                        }
                    }
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        apiClient.close()
    }
}

@Composable
fun DataItem(data: Data) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = data.title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = data.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Domain: ${data.domain}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Pwn Count: ${data.pwnCount}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Added Date: ${data.addedDate}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}
