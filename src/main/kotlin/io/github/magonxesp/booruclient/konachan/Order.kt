package io.github.magonxesp.booruclient.konachan

enum class Order(val value: String) {
	SCORE("score"),
	FAVORITED("fav"),
	WIDESCREEN_FIRST("wide"),
	WIDESCREEN_LAST("nonwide");

	fun toSearchString() = "order:$value"
}
