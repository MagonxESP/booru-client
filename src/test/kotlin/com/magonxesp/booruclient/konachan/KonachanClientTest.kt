package com.magonxesp.booruclient.konachan

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldBeIn
import kotlinx.coroutines.test.runTest

class KonachanClientTest : AnnotationSpec() {

	@Test
	fun `it should search by tag`() = runTest {
		val konachanClient = KonachanClient()
		val post = KonachanPostMother.createExisting()

		val posts = konachanClient.search {
			tag("sousou_no_frieren")
		}

		post shouldBeIn posts
	}

	@Test
	fun `it should search by tag and sorted by score`() = runTest {
		val konachanClient = KonachanClient()
		val post = KonachanPostMother.createExistingInSortedByScore()

		val posts = konachanClient.search {
			tag("sousou_no_frieren")
			order(Order.SCORE)
		}

		post shouldBeIn posts
	}
}
