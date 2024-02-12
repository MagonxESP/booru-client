package com.magonxesp.booruclient.safebooru

data class SafebooruPostCollection(
	val count: Int,
	val offset: Int,
	val posts: List<SafebooruPost>
)
