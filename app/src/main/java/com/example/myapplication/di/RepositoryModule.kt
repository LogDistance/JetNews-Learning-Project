package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.repository.AuthRepository
import com.example.myapplication.data.repository.FavoriteNewsRepository
import com.example.myapplication.data.repository.LocalAuthManager
import com.example.myapplication.data.repository.NewsRepository
import com.example.myapplication.domain.dao.FavoriteNewsDao
import com.example.myapplication.domain.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalAuthManager(@ApplicationContext context: Context): LocalAuthManager {
        return LocalAuthManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(userDao: UserDao, localAuthManager: LocalAuthManager): AuthRepository {
        return AuthRepository(userDao, localAuthManager)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(httpClient: HttpClient, favoriteNewsDao: FavoriteNewsDao): NewsRepository {
        return NewsRepository(httpClient, favoriteNewsDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteNewsRepository(favoriteNewsDao: FavoriteNewsDao): FavoriteNewsRepository {
        return FavoriteNewsRepository(favoriteNewsDao)
    }
}