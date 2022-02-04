package com.credijusto.test.di.modules

import android.app.Application
import com.credijusto.test.data.local.PostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = PostsDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: PostsDatabase) = database.getPostsDao()
}
