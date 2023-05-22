package com.example.inventory

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface Api {

    @GET("getallproducts")
    suspend fun getInven(): Response<List<All_prod>>

    @GET("getallproducts")
    suspend fun getOrder(): Response<List<OrderItem2>>

    @PATCH("cancelorder")
    suspend fun patchOrder(
        @Body item: PatchcancelorderRequest
    ):Response<ApiResponse>
}