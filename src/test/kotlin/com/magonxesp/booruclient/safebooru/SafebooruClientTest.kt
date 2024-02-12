package com.magonxesp.booruclient.safebooru

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest

class SafebooruClientTest : AnnotationSpec() {

	@Test
	fun `it should search by tag`() = runTest {
		val client = SafebooruClient()
		val response = client.search {
			tag("hololive_english")
		}

		response.posts.isNotEmpty() shouldBe true
	}

	@Test
	fun `it should search by two tags`() = runTest {
		val client = SafebooruClient()
		val response = client.search {
			tag("hololive_english")
			tag("gawr_gura")
		}

		response.posts.isNotEmpty() shouldBe true
	}

	@Test
	fun `it should search by tag negating one tag`() = runTest {
		val client = SafebooruClient()
		val response = client.search {
			tag("hololive_english")
			notHaveTag("gawr_gura")
		}

		response.posts.isNotEmpty() shouldBe true
	}

	@Test
	fun `it should search by tag and safe content`() = runTest {
		val client = SafebooruClient()
		val response = client.search {
			tag("hololive_english")
			rating(Rating.SAFE)
		}

		response.posts.isNotEmpty() shouldBe true
	}
}
