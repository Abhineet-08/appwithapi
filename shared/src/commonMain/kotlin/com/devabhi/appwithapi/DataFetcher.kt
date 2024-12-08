package com.devabhi.appwithapi

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// Data model for the breach information
@Serializable
data class Breach(
    val name: String,
    val title: String,
    val domain: String,
    val description: String,
    val logoPath: String,
    val dataClasses: List<String>
)

class DataFetcher {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true }) // To handle unknown keys
        }
    }

    suspend fun fetchBreaches(): List<Breach> {
        try {
            val response: String = client.get("https://haveibeenpwned.com/api/v2/breaches") {
                header(HttpHeaders.UserAgent, "KotlinApp") // API requires a User-Agent header
            }.body()

            // Deserialize the JSON response into List<com.devabhi.appwithapi.Breach>
            return Json.decodeFromString(response)
        } catch (e: Exception) {
            println("Error fetching breaches: ${e.message}")
            throw e
        }
    }
}
