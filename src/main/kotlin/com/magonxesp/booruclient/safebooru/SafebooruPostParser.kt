package com.magonxesp.booruclient.safebooru

import com.magonxesp.booruclient.toIntOrDefault
import com.magonxesp.booruclient.toLongOrDefault
import org.w3c.dom.Element
import org.w3c.dom.Node

fun Element.parseSafebooruPost() = SafebooruPost(
	height = getAttribute("height").toIntOrDefault(),
	score = getAttribute("score").toIntOrDefault(),
	fileUrl = getAttribute("file_url"),
	parentId = getAttribute("parent_id"),
	sampleUrl = getAttribute("sample_url"),
	sampleWidth = getAttribute("sample_width").toIntOrDefault(),
	sampleHeight = getAttribute("sample_height").toIntOrDefault(),
	previewUrl = getAttribute("preview_url"),
	rating = getAttribute("rating"),
	tags = getAttribute("tags"),
	id = getAttribute("id").toIntOrDefault(),
	width = getAttribute("width").toIntOrDefault(),
	change = getAttribute("change").toLongOrDefault(),
	md5 = getAttribute("md5"),
	creatorId = getAttribute("creator_id").toIntOrDefault(),
	hasChildren = getAttribute("has_children").toBoolean(),
	createdAt = getAttribute("created_at"),
	status = getAttribute("status"),
	source = getAttribute("source"),
	hasNotes = getAttribute("has_notes").toBoolean(),
	hasComments = getAttribute("has_comments").toBoolean(),
	previewWidth = getAttribute("preview_width").toIntOrDefault(),
	previewHeight = getAttribute("preview_height").toIntOrDefault(),
)

fun Element.parseSafebooruPostCollection(): SafebooruPostCollection {
	val posts = mutableListOf<SafebooruPost>()

	for (i in 0..<childNodes.length) {
		val node = childNodes.item(i)
		if (node.nodeType == Node.ELEMENT_NODE && node.nodeName == "post") {
			val postElement = node as Element
			posts.add(postElement.parseSafebooruPost())
		}
	}

	return SafebooruPostCollection(
		count = getAttribute("count").toIntOrDefault(),
		offset = getAttribute("offset").toIntOrDefault(),
		posts = posts
	)
}
