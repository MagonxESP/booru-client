package com.magonxesp.booruclient

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

abstract class Client {
	protected val httpClient = HttpClient(CIO)
	protected val jsonSerializer = Json { ignoreUnknownKeys = true }
	protected abstract val baseUrl: String

	protected fun fromBaseUrl(endpoint: String, queryParameters: Map<String, String>? = null): String {
		val query = queryParameters?.let {
			val parameters = it.entries.joinToString("&") { parameter -> "${parameter.key}=${parameter.value}" }
			"?$parameters"
		}

		return "$baseUrl$endpoint$query"
	}

	protected suspend fun get(endpoint: String, parameters: Map<String, String>? = null): String {
		val url = fromBaseUrl(endpoint, parameters)
		val response = httpClient.get(url)

		val responseBody = response.bodyAsText()

		if (!response.status.isSuccess()) {
			throw ClientException.RequestFailed("Request to $url failed with status code ${response.status.value} and body: $responseBody")
		}

		return responseBody
	}
}
