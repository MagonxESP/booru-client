package io.github.magonxesp.booruclient.konachan

import arrow.core.Either
import arrow.core.left
import io.github.magonxesp.booruclient.Client
import io.github.magonxesp.booruclient.ClientException
import io.github.magonxesp.booruclient.Tag
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

open class KonachanClient : Client() {
	override val baseUrl: String = "https://konachan.net"

	class Builder {
		private val criteria: MutableMap<String, String> = mutableMapOf()

		fun tag(tag: String) {
			criteria["tags"] = Tag(tag).value
		}

		fun size(compare: SizeCompare, width: Int?, height: Int?) {
			val sizeSearch = Size(compare, width, height)
			sizeSearch.widthSearch()?.also { criteria["width"] = it }
			sizeSearch.heightSearch()?.also { criteria["height"] = it }
		}

		fun order(order: Order) {
			criteria["order"] = order.toSearchString()
		}

		fun rating(rating: Rating) {
			criteria["rating"] = rating.toSearchString()
		}

		fun build() = criteria.values.joinToString(" ")
	}

	suspend fun search(setup: Builder.() -> Unit): Either<ClientException, List<KonachanPost>> =
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

			val collection = jsonSerializer.decodeFromString<List<KonachanPost>>(response.bodyAsText())
			collection
		}.mapLeft {
			if (it !is ClientException) {
				ClientException.UnknownError("An unexpected error was occurred: ${it::class}: ${it.message}")
			} else {
				it
			}
		}

}
