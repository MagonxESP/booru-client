package io.github.magonxesp.booruclient

fun String?.toIntOrDefault() =
	if (!isNullOrBlank() && toIntOrNull() != null) toInt() else 0

fun String?.toLongOrDefault() =
	if (!isNullOrBlank() && toLongOrNull() != null) toLong() else 0
