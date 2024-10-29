package com.yazdanmanesh.di

import com.yazdanmanesh.usecase.TehranArticlesUseCase
import com.yazdanmanesh.usecaseImp.TehranArticlesUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseBinderModule {
    @Binds
    @Singleton
    abstract fun tehranArticlesUseCase(tehranArticlesUseCaseImp: TehranArticlesUseCaseImp): TehranArticlesUseCase
}
