package io.github.magonxesp.booruclient.safebooru

data class SafebooruPost(
	val height: Int,
	val score: Int,
	val fileUrl: String,
	val parentId: String,
	val sampleUrl: String,
	val sampleWidth: Int,
	val sampleHeight: Int,
	val previewUrl: String,
	val rating: String,
	val tags: String,
	val id: Int,
	val width: Int,
	val change: Long,
	val md5: String,
	val creatorId: Int,
	val hasChildren: Boolean,
	val createdAt: String,
	val status: String,
	val source: String,
	val hasNotes: Boolean,
	val hasComments: Boolean,
	val previewWidth: Int,
	val previewHeight: Int
)
