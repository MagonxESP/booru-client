package io.github.magonxesp.booruclient.konachan

enum class Rating(val value: String) {
	SAFE("safe"),
	QUESTIONABLE("questionable"),
	EXPLICIT("explicit"),
	QUESTIONABLE_PLUS("questionableplus"),
	QUESTIONABLE_LESS("questionableless");

	fun toSearchString() = "rating:$value"

	companion object {
		fun fromValue(value: String): Rating? {
			return entries.find { it.value == value }
		}
	}
}
