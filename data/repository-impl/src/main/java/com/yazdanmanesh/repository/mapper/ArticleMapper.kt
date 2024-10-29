package com.yazdanmanesh.repository.mapper

import com.yazdanmanesh.cache.model.ArticleEntity
import com.yazdanmanesh.cache.model.SourceEntity
import com.yazdanmanesh.common_entity.Article
import com.yazdanmanesh.common_entity.Source
import com.yazdanmanesh.network.dto.ArticleDto
import com.yazdanmanesh.network.dto.SourceDto
import javax.inject.Inject

class ArticleMapper @Inject constructor() {
    fun articleEntityToDomain(entity: ArticleEntity): Article {
        return Article(
            author = entity.author,
            content = entity.content,
            description = entity.description,
            publishedAt = entity.publishedAt,
            source = sourceEntityToDomain(entity.source),
            title = entity.title,
            url = entity.url,
            urlToImage = entity.urlToImage,
            id = entity.id
        )
    }

    fun articleDtoToEntity(dto: ArticleDto): ArticleEntity {
        return ArticleEntity(
            author = dto.author,
            content = dto.content,
            description = dto.description,
            publishedAt = dto.publishedAt,
            source = sourceDtoToEntity(dto.source),
            title = dto.title,
            url = dto.url,
            urlToImage = dto.urlToImage
        )
    }

    private fun articleEntityToDto(entity: ArticleEntity): ArticleDto {
        return ArticleDto(
            author = entity.author,
            content = entity.content,
            description = entity.description,
            publishedAt = entity.publishedAt,
            source = sourceEntityToDto(entity.source),
            title = entity.title,
            url = entity.url,
            urlToImage = entity.urlToImage
        )
    }

    fun listArticleEntityToDto(entityList: List<ArticleEntity>): List<ArticleDto> {
        return entityList.map { articleEntityToDto(it) }
    }

    private fun sourceEntityToDomain(entity: SourceEntity): Source {
        return Source(
            name = entity.name,
            id = entity.id
        )
    }

    private fun sourceDtoToEntity(dto: SourceDto): SourceEntity {
        return SourceEntity(
            name = dto.name,
            id = dto.id
        )
    }

    private fun sourceEntityToDto(entity: SourceEntity): SourceDto {
        return SourceDto(
            name = entity.name,
            id = entity.id
        )
    }
}
