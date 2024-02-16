package io.github.magonxesp.booruclient.safebooru

import io.github.magonxesp.booruclient.Client
import io.github.magonxesp.booruclient.ClientException
import io.github.magonxesp.booruclient.Tag
import org.xml.sax.InputSource
import java.io.StringReader
import java.net.URLEncoder
import javax.xml.parsers.DocumentBuilderFactory

class SafebooruClient : Client() {
	override val baseUrl: String = "https://safebooru.org"

	class Builder {
		private val queryParameters: MutableMap<String, String> = mutableMapOf(
			"page" to "dapi",
			"s" to "post",
			"q" to "index",
		)

		private val tags = mutableListOf<String>()

		/**
		 * Search posts by tag, adding other tags with [tag] method will search
		 * by posts that contains all tags added to search
		 */
		fun tag(tag: String) {
			tags.add(Tag(tag).value)
		}

		/**
		 * Search posts that don't have this tag
		 */
		fun notHaveTag(tag: String) {
			tags.add("-${Tag(tag).value}")
		}

		/**
		 * Search posts by tags that [startsWith] and [endsWith]
		 */
		fun startsAndEndWith(startsWith: String, endsWith: String) {
			tags.add("${Tag(startsWith).value}*$${Tag(endsWith).value}")
		}

		/**
		 * Search by user
		 */
		fun user(user: String) {
			tags.add("user:${Tag(user).value}")
		}

		/**
		 * Search for posts with the MD5 hash.
		 */
		fun md5(md5: String) {
			tags.add("md5:$md5")
		}

		/**
		 * Search for posts whose MD5 starts with the MD5 hash foo.
		 */
		fun startsWithMd5(md5: String) {
			tags.add("md5:$md5*")
		}

		/**
		 * Search posts by [rating]
		 */
		fun rating(rating: Rating) {
			tags.add("rating:${rating.value}")
		}

		/**
		 * Search posts that not have [rating]
		 */
		fun notHaveRating(rating: Rating) {
			tags.add("-rating:${rating.value}")
		}

		/**
		 * Search posts that have [parent]
		 */
		fun parent(parent: String) {
			tags.add("parent:$parent")
		}

		/**
		 * Search posts by width greater than [width]
		 */
		fun widthGreaterThan(width: Int) {
			tags.add("width:>$width")
		}

		/**
		 * Search posts by width greater or equal than [width]
		 */
		fun widthGreaterOrEqualThan(width: Int) {
			tags.add("width:>=$width")
		}

		/**
		 * Search posts by width equal than [width]
		 */
		fun widthEqualThan(width: Int) {
			tags.add("width:=$width")
		}

		/**
		 * Search posts by width less than [width]
		 */
		fun widthLessThan(width: Int) {
			tags.add("width:<$width")
		}

		/**
		 * Search posts by width less or equal than [width]
		 */
		fun widthLessOrEqualThan(width: Int) {
			tags.add("width:<=$width")
		}

		/**
		 * Search posts by height greater than [height]
		 */
		fun heightGreaterThan(height: Int) {
			tags.add("height:>$height")
		}

		/**
		 * Search posts by height greater or equal than [height]
		 */
		fun heightGreaterOrEqualThan(height: Int) {
			tags.add("height:>=$height")
		}

		/**
		 * Search posts by height equal than [height]
		 */
		fun heightEqualThan(height: Int) {
			tags.add("height:=$height")
		}

		/**
		 * Search posts by height less than [height]
		 */
		fun heightLessThan(height: Int) {
			tags.add("height:<$height")
		}

		/**
		 * Search posts by height less or equal than [height]
		 */
		fun heightLessOrEqualThan(height: Int) {
			tags.add("height:<=$height")
		}

		/**
		 * Search posts by score greater than [score]
		 */
		fun scoreGreaterThan(score: Int) {
			tags.add("score:>$score")
		}

		/**
		 * Search posts by score greater or equal than [score]
		 */
		fun scoreGreaterOrEqualThan(score: Int) {
			tags.add("score:>=$score")
		}

		/**
		 * Search posts by score equal than [score]
		 */
		fun scoreEqualThan(score: Int) {
			tags.add("score:=$score")
		}

		/**
		 * Search posts by score less than [score]
		 */
		fun scoreLessThan(score: Int) {
			tags.add("score:<$score")
		}

		/**
		 * Search posts by score less or equal than [score]
		 */
		fun scoreLessOrEqualThan(score: Int) {
			tags.add("score:<=$score")
		}

		/**
		 * Sort posts by [type]
		 */
		fun sortBy(type: SortType, order: Order = Order.ASC) {
			tags.add("sort:${type.value}:${order.value}")
		}

		/**
		 * Set the posts limit, it should be greater than 0 or will throw [ClientException.InvalidLimit]
		 *
		 * @throws ClientException.InvalidLimit
		 */
		fun limit(limit: Int) {
			if (limit < 1) {
				throw ClientException.InvalidLimit("The limit should be greater than 0")
			}

			queryParameters["limit"] = limit.toString()
		}

		/**
		 * Set the page number for the pager, It should be grater than 0 or will throw [ClientException.InvalidPage]
		 *
		 * @throws ClientException.InvalidPage
		 */
		fun page(page: Int) {
			if (page < 1) {
				throw ClientException.InvalidPage("The page should be greater than 0")
			}

			queryParameters["pid"] = page.toString()
		}

		/**
		 * Change ID of the post. This is in Unix time so there are likely others with the same value if updated at the same time.
		 */
		fun cid(cid: String) {
			queryParameters["cid"] = cid
		}

		/**
		 * The post id
		 */
		fun id(id: String) {
			queryParameters["cid"] = id
		}

		/**
		 * Show the deleted posts
		 */
		fun showDeleted() {
			queryParameters["deleted"] = "show"
		}

		/**
		 * Build the query string for perform the search
		 */
		fun build(): Map<String, String> {
			if (tags.isNotEmpty()) {
				val tagsEncoded = URLEncoder.encode(tags.joinToString(" "), "utf-8")
				queryParameters["tags"] = tagsEncoded
			}

			return queryParameters
		}
	}

	private fun parseXmlPosts(xml: String): SafebooruPostCollection {
		val factory = DocumentBuilderFactory.newInstance()
		val builder = factory.newDocumentBuilder()
		val reader = InputSource(StringReader(xml))
		val document = builder.parse(reader)

		val root = document.documentElement

		if (root.tagName == "response") {
			val response = root.parseSafebooruResponse()
			throw ClientException.RequestFailed("Safebooru request failed: ${response.reason}")
		}

		if (root.tagName != "posts") {
			throw ClientException.ParseError("Error parsing safebooru xml: posts element not found")
		}

		return root.parseSafebooruPostCollection()
	}

	suspend fun search(setup: Builder.() -> Unit): SafebooruPostCollection {
		val builder = Builder()
		builder.setup()

		val rawXml = get("/index.php", builder.build())
		return parseXmlPosts(rawXml)
	}
}
