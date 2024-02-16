package io.github.magonxesp.booruclient.safebooru

import org.w3c.dom.Element

fun Element.parseSafebooruResponse() = SafebooruResponse(
	success = getAttribute("success").toBoolean(),
	reason = getAttribute("reason")
)
