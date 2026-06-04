package com.example.myapplication.di

import android.content.Context
import androidx.room.Room

import com.example.myapplication.data.database.UserDatabase
import com.example.myapplication.domain.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) // БЕЗ ЭТОГО НЕ ЗАРАБОТАЕТ HILT
object DatabaseModule {

    @Provides
    @Singleton // Чтобы база была в одном экземпляре
    fun providesUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "userDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.getUserDao()
    }
}