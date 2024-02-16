package io.github.magonxesp.booruclient.danbooru

import io.github.magonxesp.booruclient.Client
import io.github.magonxesp.booruclient.Tag

class DanbooruClient : Client() {
	override val baseUrl: String = "https://danbooru.donmai.us/"

	class Builder {
		private val criteria: MutableMap<String, String> = mutableMapOf()

		fun tag(tag: String) {
			criteria["tags"] = Tag(tag).value
		}

		fun build() = criteria.values.joinToString(" ")
	}

	suspend fun search(setup: Builder.() -> Unit): List<DanbooruPost> {
		val builder = Builder()
		builder.setup()

		val searchTag = builder.build()
		val rawJson = get("/posts.json", mapOf("tags" to searchTag))
		return jsonSerializer.decodeFromString<List<DanbooruPost>>(rawJson)
	}
}
