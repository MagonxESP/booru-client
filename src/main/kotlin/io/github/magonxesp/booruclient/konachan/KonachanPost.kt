package io.github.magonxesp.booruclient.konachan

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class KonachanPost(
	@SerialName("id") val id: Int,
	@SerialName("tags") val tags: String,
	@SerialName("created_at") val createdAt: Long,
	@SerialName("creator_id") val creatorId: Int,
	@SerialName("author") val author: String,
	@SerialName("change") val change: Int,
	@SerialName("source") val source: String,
	@SerialName("score") val score: Int,
	@SerialName("md5") val md5: String,
	@SerialName("file_size") val fileSize: Long,
	@SerialName("file_url") val fileUrl: String,
	@SerialName("is_shown_in_index") val isShownInIndex: Boolean,
	@SerialName("preview_url") val previewUrl: String,
	@SerialName("preview_width") val previewWidth: Int,
	@SerialName("preview_height") val previewHeight: Int,
	@SerialName("actual_preview_width") val actualPreviewWidth: Int,
	@SerialName("actual_preview_height") val actualPreviewHeight: Int,
	@SerialName("sample_url") val sampleUrl: String,
	@SerialName("sample_width") val sampleWidth: Int,
	@SerialName("sample_height") val sampleHeight: Int,
	@SerialName("sample_file_size") val sampleFileSize: Int,
	@SerialName("jpeg_url") val jpegUrl: String,
	@SerialName("jpeg_width") val jpegWidth: Int,
	@SerialName("jpeg_height") val jpegHeight: Int,
	@SerialName("jpeg_file_size") val jpegFileSize: Long,
	@SerialName("rating") val rating: String,
	@SerialName("has_children") val hasChildren: Boolean,
	@SerialName("parent_id") val parentId: Int?, // Nullable due to possible absence in JSON
	@SerialName("status") val status: String,
	@SerialName("width") val width: Int,
	@SerialName("height") val height: Int,
	@SerialName("is_held") val isHeld: Boolean,
// TODO: resolve the frames types
//	@SerialName("frames_pending_string") val framesPendingString: String,
//	@SerialName("frames_pending") val framesPending: List<Any>, // Assuming a list of any type due to variability
//	@SerialName("frames_string") val framesString: String,
//	@SerialName("frames") val frames: List<Any> // Assuming a list of any type due to variability
)
