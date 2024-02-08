package com.magonxesp.booruclient.yandere

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest

class YandereClientTest : AnnotationSpec() {

	@Test
	fun `it should search by tag`() = runTest {
		val konachanClient = YandereClient()

		val posts = konachanClient.search {
			tag("sousou_no_frieren")
		}

		true shouldBe posts.isNotEmpty()
	}

}
