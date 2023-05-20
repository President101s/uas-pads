package com.example.inventory

import retrofit2.Response
import retrofit2.http.GET

interface InvenApi {

    @GET("getallproducts")
    suspend fun getInven(): Response<List<Items>>

}