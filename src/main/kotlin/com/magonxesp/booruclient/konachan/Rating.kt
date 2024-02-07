package com.magonxesp.booruclient.konachan

import arrow.core.Option
import arrow.core.toOption

enum class Rating(val value: String) {
	SAFE("safe"),
	QUESTIONABLE("questionable"),
	EXPLICIT("explicit"),
	QUESTIONABLE_PLUS("questionableplus"),
	QUESTIONABLE_LESS("questionableless");

	fun toSearchString() = "rating:$value"

	companion object {
		fun fromValue(value: String): Option<Rating> {
			return entries.find { it.value == value }.toOption()
		}
	}
}
