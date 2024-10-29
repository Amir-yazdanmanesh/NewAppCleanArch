package com.yazdanmanesh.network.di

import com.example.yazdanmanesh.network.BuildConfig.DEBUG
import com.yazdanmanesh.network.service.NewsApi
import com.yazdanmanesh.network.utils.Constants.BASE_URL
import com.yazdanmanesh.network.utils.Constants.REQUEST_CONNECT_TIMEOUT_SECOND
import com.yazdanmanesh.network.utils.Constants.REQUEST_READ_TIMEOUT_SECOND
import com.yazdanmanesh.network.utils.Constants.REQUEST_WRITE_TIMEOUT_SECOND
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(REQUEST_READ_TIMEOUT_SECOND, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_WRITE_TIMEOUT_SECOND, TimeUnit.SECONDS)
            .connectTimeout(REQUEST_CONNECT_TIMEOUT_SECOND, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }
}
