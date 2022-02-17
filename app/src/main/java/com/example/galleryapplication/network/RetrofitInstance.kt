package com.example.galleryapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitInstance @Inject constructor() {

    fun getRetrofitClient(): Retrofit {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!
    }

    companion object {

        var retrofit: Retrofit? = null
        private var BASE_URL = "https://pixabay.com/"

    }
}