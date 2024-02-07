package io.github.magonxesp.booruclient

import io.ktor.client.*
import io.ktor.client.engine.cio.*
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
}
