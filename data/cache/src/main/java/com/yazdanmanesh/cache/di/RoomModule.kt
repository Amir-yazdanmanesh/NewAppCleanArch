package com.yazdanmanesh.cache.di

import android.content.Context
import androidx.room.Room
import com.yazdanmanesh.cache.TehranArticlesDao
import com.yazdanmanesh.cache.AppDatabase
import com.yazdanmanesh.cache.RemoteKeysDao
import com.yazdanmanesh.cache.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context ,AppDatabase::class.java ,DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTehranArticlesDao(appDatabase: AppDatabase): TehranArticlesDao {
        return appDatabase.getTehranArticlesDao()
    }

    @Provides
    fun provideRemoteKeysDao(appDatabase: AppDatabase): RemoteKeysDao {
        return appDatabase.getRemoteKeysDao()
    }
}
