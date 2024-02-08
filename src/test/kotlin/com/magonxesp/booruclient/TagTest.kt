package com.magonxesp.booruclient

import io.github.magonxesp.booruclient.ClientException
import io.github.magonxesp.booruclient.Tag
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.equals.shouldBeEqual

class TagTest : AnnotationSpec() {
	@Test
	fun `it should be snake case`() {
		val tag = Tag("sousou_no_frieren")

		"sousou_no_frieren" shouldBeEqual tag.value
	}

	@Test
	fun `it should be snake case allowing wildcard`() {
		val tag = Tag("sousou_no_frieren*")

		"sousou_no_frieren*" shouldBeEqual tag.value
	}

	@Test
	fun `it should throw an exception if is not snake case`() {
		shouldThrowExactly<ClientException.InvalidTagFormat> {
			Tag("Sousou no frieren")
		}
	}
}
