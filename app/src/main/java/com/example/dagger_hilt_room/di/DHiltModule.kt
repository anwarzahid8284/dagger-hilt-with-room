package com.example.dagger_hilt_room.di

import android.content.Context
import androidx.room.Room
import com.example.dagger_hilt_room.room.UserDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DHiltModule {
    @Singleton
    @Provides
    fun roomProvider(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserDB::class.java, "User_DB")
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun daoProvider(userDB: UserDB) = userDB.appDao()
}