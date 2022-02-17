package com.example.galleryapplication.di

import android.content.Context
import androidx.room.Room
import com.example.galleryapplication.network.ApiService
import com.example.galleryapplication.repository.ImageDao
import com.example.galleryapplication.repository.ImageDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ImageDataBase {
        return Room.databaseBuilder(
            appContext,
            ImageDataBase::class.java,
            "note_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(appDatabase: ImageDataBase): ImageDao {
        return appDatabase.getNoteDao()
    }

    @Provides
    fun provideApiService(retrofitInstance: Retrofit): ApiService {
        return retrofitInstance.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}