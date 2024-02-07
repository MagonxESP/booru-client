package io.github.magonxesp.booruclient.yandere

import io.github.magonxesp.booruclient.yandere.YandereClient
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.test.runTest

class YandereClientTest : AnnotationSpec() {

	@Test
	fun `it should search by tag`() = runTest {
		val konachanClient = YandereClient()

		val posts = konachanClient.search {
			tag("sousou_no_frieren")
		}

		posts.leftOrNull() shouldBe null
		posts.getOrNull() shouldNotBe null
		true shouldBe posts.getOrNull()!!.isNotEmpty()
	}

}
