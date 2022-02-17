package com.example.galleryapplication.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.galleryapplication.models.ImageTable

@Database(
    entities = [ImageTable::class],
    version = 1,
    exportSchema = false
)
abstract class ImageDataBase : RoomDatabase() {

    abstract fun getNoteDao(): ImageDao

    companion object {
        private const val DB_NAME = "note_database.db"
        @Volatile
        private var instance: ImageDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ImageDataBase::class.java,
            DB_NAME
        ).build()
    }
}