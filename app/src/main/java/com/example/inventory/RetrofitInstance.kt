package com.example.inventory

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.252.246.219:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}