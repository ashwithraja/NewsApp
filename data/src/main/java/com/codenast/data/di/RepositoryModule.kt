package com.codenast.data.di

import com.codenast.data.repository.NewsRepositoryImp
import com.codenast.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(repositoryImp: NewsRepositoryImp) : NewsRepository {
        return repositoryImp;
    }
}