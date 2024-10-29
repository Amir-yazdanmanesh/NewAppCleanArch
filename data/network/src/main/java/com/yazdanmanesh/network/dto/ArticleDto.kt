package com.yazdanmanesh.network.dto

data class ArticleDto(
    val author: String?,
    val content: String,
    val description: String?,
    val publishedAt: String,
    val source: SourceDto,
    val title: String,
    val url: String,
    val urlToImage: String?,
    )

fun generateFakeDtoArticles(count: Int): List<ArticleDto> {
    val fakeArticles = mutableListOf<ArticleDto>()
    for (i in 1..count) {
        val fakeAuthor = "Author $i"
        val fakeTitle = "Fake Title $i"
        val fakeDescription = "This is a fake description for article $i"
        val fakeContent = "This is the fake content for article $i. It is long and detailed."
        val fakePublishedAt = "2024-10-26T16:36:00Z"
        val fakeSource = SourceDto(
            id = "source$i",
            name = "Fake Source $i",
        )
        val fakeUrl = "https://example.com/article$i"
        val fakeUrlToImage = "https://example.com/image$i.jpg"
        val fakeArticle = ArticleDto(
            author = fakeAuthor,
            content = fakeContent,
            description = fakeDescription,
            publishedAt = fakePublishedAt,
            source = fakeSource,
            title = fakeTitle,
            url = fakeUrl,
            urlToImage = fakeUrlToImage,
        )
        fakeArticles.add(fakeArticle)
    }
    return fakeArticles
}

fun getNewsDto(count: Int): NewsDto {
    return NewsDto(
        totalResults = count,
        status = "ok",
        articles = generateFakeDtoArticles(count)
    )
}