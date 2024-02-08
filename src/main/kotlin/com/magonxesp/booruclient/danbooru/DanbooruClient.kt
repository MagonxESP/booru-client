package com.magonxesp.booruclient.danbooru

import arrow.core.Either
import arrow.core.left
import com.magonxesp.booruclient.ClientException
import com.magonxesp.booruclient.Tag
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class DanbooruClient : com.magonxesp.booruclient.Client() {
	override val baseUrl: String = "https://danbooru.donmai.us/"

	class Builder {
		private val criteria: MutableMap<String, String> = mutableMapOf()

		fun tag(tag: String) {
			criteria["tags"] = Tag(tag).value
		}

		fun build() = criteria.values.joinToString(" ")
	}

	suspend fun search(setup: Builder.() -> Unit): Either<ClientException, List<DanbooruPost>> =
		Either.catch {
			val builder = Builder()
			builder.setup()

			val searchTag = builder.build()
			val url = fromBaseUrl("/posts.json", mapOf("tags" to searchTag))
			val response = httpClient.get(url)

			if (!response.status.isSuccess()) {
				return ClientException.RequestFailed("Request to $url failed with status code ${response.status.value} and body: ${response.bodyAsText()}")
					.left()
			}

			val collection = jsonSerializer.decodeFromString<List<DanbooruPost>>(response.bodyAsText())
			collection
		}.mapLeft {
			if (it !is ClientException) {
				ClientException.UnknownError("An unexpected error was occurred: ${it::class}: ${it.message}")
			} else {
				it
			}
		}

}
