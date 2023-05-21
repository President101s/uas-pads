package com.example.inventory

import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("getallproducts")
    suspend fun getInven(): Response<List<All_prod>>

    @GET("getallproducts")
    suspend fun getOrder(): Response<List<OrderItem>>

}