package com.example.galleryapplication.repository

import androidx.lifecycle.LiveData
import com.example.galleryapplication.models.ImageTable
import javax.inject.Inject

class ImageRepository
        @Inject constructor(
        private val imageDataBase: ImageDao
    ) {

    fun insertImage(image: ImageTable) = imageDataBase.insertImage(image)

    fun deleteImage(url: String) = imageDataBase.deleteImage(url)

    fun getCount(url: String) = imageDataBase.getCount(url)

    fun getAllImages(): LiveData<List<ImageTable>> = imageDataBase.getAllImages()

}