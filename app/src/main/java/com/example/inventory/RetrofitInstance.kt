package com.example.inventory

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: InvenApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.252.244.24:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InvenApi::class.java)
    }

}