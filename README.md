# Booru client

Danbooru, yande.re and konachan client for kotlin

## Usage

Search by tag on Konachan for example.

```kotlin
val client = KonachanClient()

val posts = client.search {
    tag("sousou_no_frieren")
}
```

The `search` method has a lambda that builds the search query.

For the Konachan client has the following methods:
* `tag`
* `size`
* `order`
* `rating`

The other clients have only the `tag` method for search.

### Available clients

* `com.magonxesp.booruclient.danbooru.DanbooruClient` The Danbooru client
* `com.magonxesp.booruclient.konachan.KonachanClient` The Konachan sfw client
* `com.magonxesp.booruclient.konachan.KonachanNsfwClient` The Konachan nsfw client
* `com.magonxesp.booruclient.yandere.YandereClient` The Yande.re client
