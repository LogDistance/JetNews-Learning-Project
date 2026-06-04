package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.repository.AuthRepository
import com.example.myapplication.data.repository.LocalAuthManager
import com.example.myapplication.data.repository.NewsRepository
import com.example.myapplication.domain.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{
    @Provides
    @Singleton
    fun provideAuthRepository(userDao: UserDao): AuthRepository{
        return AuthRepository(userDao)
    }

    @Provides
    @Singleton
    fun provideLocalAuthManager(@ApplicationContext context: Context): LocalAuthManager{
        return LocalAuthManager(context)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(): NewsRepository {
        return NewsRepository()
    }
}