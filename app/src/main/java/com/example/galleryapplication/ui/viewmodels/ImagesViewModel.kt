package com.example.galleryapplication.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryapplication.models.Hit
import com.example.galleryapplication.models.Images
import com.example.galleryapplication.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private var apiService: ApiService
) : ViewModel() {
    private var imagesLiveData: MutableLiveData<List<Hit>>? = null
//    private var apiService: ApiService

    init {
        this.imagesLiveData = MutableLiveData()
//        val retrofitInstance = RetrofitInstance()
//        apiService = retrofitInstance.getRetrofitClient().create(ApiService::class.java)
    }

    fun getImagesListObserver(): MutableLiveData<List<Hit>>? {
        return imagesLiveData
    }


    fun searchApi(searchText: String) {

        val call: Call<Images> =
            apiService.getImages("20407077-ea8f89160b9cdfff4a6672d3d", searchText, "vertical")
        Log.d("TAG", "searchApi: ")

        call.enqueue(object : Callback<Images> {
            override fun onResponse(call: Call<Images>, response: Response<Images>) {
                Log.d("TAG", "onResponse: URL " + call.request().url)
                Log.d("TAG", "onResponse: RESPONSE " + response.body()?.total)
                Log.d("TAG", "onResponse: RESPONSE " + response.body()?.totalHits)
                Log.d("TAG", "onResponse: RESPONSE " + response.body()?.hits!![0])
                imagesLiveData?.postValue(response.body()?.hits)
            }

            override fun onFailure(call: Call<Images>, t: Throwable) {
                Log.e("TAG", "onFailure: ", t)
            }

        })

    }

}