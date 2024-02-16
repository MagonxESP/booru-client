package io.github.magonxesp.booruclient.konachan

import io.github.magonxesp.booruclient.Client
import io.github.magonxesp.booruclient.Tag

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

	suspend fun search(setup: Builder.() -> Unit): List<KonachanPost> {
		val builder = Builder()
		builder.setup()

		val searchTag = builder.build()
		val rawJson = get("/post.json", mapOf("tags" to searchTag))
		return jsonSerializer.decodeFromString<List<KonachanPost>>(rawJson)
	}

}
