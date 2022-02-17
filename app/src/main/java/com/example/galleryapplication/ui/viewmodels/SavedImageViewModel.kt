package com.example.galleryapplication.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryapplication.models.ImageTable
import com.example.galleryapplication.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedImageViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    var count = MutableLiveData<Boolean>()


    fun insertImage(image: ImageTable) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                repository.insertImage(image)
            }

        } catch (e: Exception) {

        }
    }

    fun deleteImage(url: String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                repository.deleteImage(url)
            }
        } catch (e: Exception) {

        }
    }

    fun getCount(url: String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                count.postValue(repository.getCount(url))
            }

        } catch (e: Exception) {

        }
    }

    fun getAllImages() = repository.getAllImages()

}