package io.github.magonxesp.booruclient.danbooru

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaAsset(
	@SerialName("id") val id: Long,
	@SerialName("created_at") val createdAt: String,
	@SerialName("updated_at") val updatedAt: String,
	@SerialName("md5") val md5: String,
	@SerialName("file_ext") val fileExt: String,
	@SerialName("file_size") val fileSize: Long,
	@SerialName("image_width") val imageWidth: Int,
	@SerialName("image_height") val imageHeight: Int,
	@SerialName("duration") val duration: String?,
	@SerialName("status") val status: String,
	@SerialName("file_key") val fileKey: String,
	@SerialName("is_public") val isPublic: Boolean,
	@SerialName("pixel_hash") val pixelHash: String,
	@SerialName("variants") val variants: List<Variant>
)
