package com.yazdanmanesh.common_entity

import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class Article(
    val author: String?,
    val content: String,
    val description: String?,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?,
    val id: Long
)

fun generateFakeArticles(count: Int): List<Article> {
    val fakeArticles = mutableListOf<Article>()
    for (i in 1..count) {
        val fakeAuthor = "Author $i"
        val fakeTitle = "Fake Title $i"
        val fakeDescription = "This is a fake description for article $i"
        val fakeContent = "This is the fake content for article $i. It is long and detailed."
        val fakePublishedAt = Instant.now().toString()
        val fakeSource = Source(
            id = "source$i",
            name = "Fake Source $i",
        )
        val fakeUrl = "https://example.com/article$i"
        val fakeUrlToImage = "https://example.com/image$i.jpg"
        val fakeId = i.toLong()

        val fakeArticle = Article(
            author = fakeAuthor,
            content = fakeContent,
            description = fakeDescription,
            publishedAt = fakePublishedAt,
            source = fakeSource,
            title = fakeTitle,
            url = fakeUrl,
            urlToImage = fakeUrlToImage,
            id = fakeId
        )
        fakeArticles.add(fakeArticle)
    }
    return fakeArticles
}