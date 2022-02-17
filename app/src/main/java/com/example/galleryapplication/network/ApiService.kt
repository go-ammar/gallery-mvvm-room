package com.example.galleryapplication.network

import com.example.galleryapplication.models.Images
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /*
 All endpoints go in this interface
  */

    @GET("api")
    fun getImages(
        @Query("key") key: String,
        @Query("q") searchQuery: String,
        @Query("orientation") orientation: String
    ): Call<Images>

}