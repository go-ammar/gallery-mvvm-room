package com.example.galleryapplication.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.galleryapplication.models.ImageTable

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //if some data is same/conflict, it'll be replace with new data.
    fun insertImage(image: ImageTable)

    @Query("SELECT EXISTS(SELECT * FROM ImageTable WHERE id = :id)")
    fun getCount(id: String): Boolean

    @Query("DELETE FROM ImageTable WHERE id = :id")
    fun deleteImage(id: String)

    @Query("SELECT * FROM ImageTable")
    fun getAllImages(): LiveData<List<ImageTable>>
    // why not use suspend ? because Room does not support LiveData with suspended functions.
    // LiveData already works on a background thread and should be used directly without using coroutines

    @Query("DELETE FROM ImageTable")
    fun clearImage()

}