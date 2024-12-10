package com.devabhi.appwithapi

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ApiClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchData(): List<Data> {
        return try {
            val responseText = client.get("https://haveibeenpwned.com/api/v2/breaches").bodyAsText()
            println("Raw response: $responseText") // Log the raw JSON response
            val parsedData = Json.decodeFromString<List<Data>>(responseText) // Decode as List<Data>
            println("Parsed data: $parsedData") // Log the parsed data
            parsedData
        } catch (e: kotlinx.serialization.SerializationException) {
            println("Serialization error: ${e.message}")
            emptyList()
        } catch (e: Exception) {
            println("Error fetching data: ${e.message}")
            emptyList()
        }
    }






    fun close() {
        client.close()
    }
}
