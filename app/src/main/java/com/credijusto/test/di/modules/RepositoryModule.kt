package com.credijusto.test.di.modules

import com.credijusto.test.data.repository.DefaultPostRepository
import com.credijusto.test.data.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Currently PostRepository is only used in ViewModels.
 */
@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class RepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindPostRepository(repository: DefaultPostRepository): PostRepository
}
