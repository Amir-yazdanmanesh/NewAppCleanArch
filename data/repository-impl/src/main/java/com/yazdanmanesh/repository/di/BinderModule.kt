package com.yazdanmanesh.repository.di

import com.yazdanmanesh.repository.repository.TehranArticlesRepositoryImpl
import com.yazdanmanesh.repository.TehranArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {
    @Binds
    abstract fun tehranArticlesRepository(articlesRepositoryImpl: TehranArticlesRepositoryImpl): TehranArticlesRepository
}