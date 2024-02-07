package io.github.magonxesp.booruclient.danbooru

import io.github.magonxesp.booruclient.danbooru.DanbooruClient
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.test.runTest

class DanbooruClientTest : AnnotationSpec() {

	@Test
	fun `it should search by tag`() = runTest {
		val konachanClient = DanbooruClient()

		val posts = konachanClient.search {
			tag("sousou_no_frieren")
		}

		posts.leftOrNull() shouldBe null
		posts.getOrNull() shouldNotBe null
		true shouldBe posts.getOrNull()!!.isNotEmpty()
	}

}
