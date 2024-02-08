package com.magonxesp.booruclient.danbooru

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest

class DanbooruClientTest : AnnotationSpec() {

	@Test
	fun `it should search by tag`() = runTest {
		val konachanClient = DanbooruClient()

		val posts = konachanClient.search {
			tag("sousou_no_frieren")
		}

		true shouldBe posts.isNotEmpty()
	}

}
