package com.magonxesp.booruclient.yandere

import arrow.core.Either
import arrow.core.left
import com.magonxesp.booruclient.Client
import io.github.magonxesp.booruclient.ClientException
import io.github.magonxesp.booruclient.Tag
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class YandereClient : com.magonxesp.booruclient.Client() {
	override val baseUrl: String = "https://yande.re/"

	class Builder {
		private val criteria: MutableMap<String, String> = mutableMapOf()

		fun tag(tag: String) {
			criteria["tags"] = Tag(tag).value
		}

		fun build() = criteria.values.joinToString(" ")
	}

	suspend fun search(setup: Builder.() -> Unit): Either<ClientException, List<YanderePost>> =
		Either.catch {
			val builder = Builder()
			builder.setup()

			val searchTag = builder.build()
			val url = fromBaseUrl("/post.json", mapOf("tags" to searchTag))
			val response = httpClient.get(url)

			if (!response.status.isSuccess()) {
				return ClientException.RequestFailed("Request to $url failed with status code ${response.status.value} and body: ${response.bodyAsText()}")
					.left()
			}

			val collection = jsonSerializer.decodeFromString<List<YanderePost>>(response.bodyAsText())
			collection
		}.mapLeft {
			if (it !is ClientException) {
				ClientException.UnknownError("An unexpected error was occurred: ${it::class}: ${it.message}")
			} else {
				it
			}
		}

}
