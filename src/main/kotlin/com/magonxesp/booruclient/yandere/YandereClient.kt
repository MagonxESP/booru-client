package com.magonxesp.booruclient.yandere

import com.magonxesp.booruclient.Client
import com.magonxesp.booruclient.Tag

class YandereClient : Client() {
	override val baseUrl: String = "https://yande.re/"

	class Builder {
		private val criteria: MutableMap<String, String> = mutableMapOf()

		fun tag(tag: String) {
			criteria["tags"] = Tag(tag).value
		}

		fun build() = criteria.values.joinToString(" ")
	}

	suspend fun search(setup: Builder.() -> Unit): List<YanderePost> {
		val builder = Builder()
		builder.setup()

		val searchTag = builder.build()
		val rawJson = get("/post.json", mapOf("tags" to searchTag))
		return jsonSerializer.decodeFromString<List<YanderePost>>(rawJson)
	}

}
