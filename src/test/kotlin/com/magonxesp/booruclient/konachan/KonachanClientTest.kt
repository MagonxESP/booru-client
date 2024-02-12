package com.magonxesp.booruclient.konachan

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest

class KonachanClientTest : AnnotationSpec() {

	@Test
	fun `it should search by tag`() = runTest {
		val konachanClient = KonachanClient()

		val posts = konachanClient.search {
			tag("sousou_no_frieren")
		}

		posts.isNotEmpty() shouldBe true
	}

	@Test
	fun `it should search by tag and sorted by score`() = runTest {
		val konachanClient = KonachanClient()

		val posts = konachanClient.search {
			tag("sousou_no_frieren")
			order(Order.SCORE)
		}

		posts.isNotEmpty() shouldBe true
	}
}
