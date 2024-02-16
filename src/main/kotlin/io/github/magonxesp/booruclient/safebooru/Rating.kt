package io.github.magonxesp.booruclient.safebooru

enum class Rating(val value: String) {
	SAFE("safe"),
	/**
	 * Safebooru only has safe content, the explicit rating don't work
	 */
	EXPLICIT("explicit"),
	QUESTIONABLE("questionable")
}
