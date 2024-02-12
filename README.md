# Booru client
[![](https://jitpack.io/v/magonxesp/booru-client.svg)](https://jitpack.io/#magonxesp/booru-client)

Danbooru, yande.re and konachan client for kotlin

## Instalation

Add the jitpack repository

```kotlin
repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}
```

And the dependency and the required dependencies

```kotlin
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_corroutines_version") // required
implementation("com.github.magonxesp:booru-client:0.1.0")
```

## Usage

Search by tag on Konachan for example.

```kotlin
import com.magonxesp.booruclient.konachan.KonachanClient
import kotlinx.coroutines.runBlocking

runBlocking {
    val client = KonachanClient()

    client.search {
        tag("sousou_no_frieren")
    }.forEach { post ->
        println(post.previewUrl)
    }
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
* `com.magonxesp.booruclient.safebooru.SafebooruClient.kt` The Safebooru client
