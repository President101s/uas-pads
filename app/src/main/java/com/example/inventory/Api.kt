package com.example.inventory

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("getallproducts")
    suspend fun getInven(): Response<List<All_prod>>

    @GET("getorders/salesA")
    suspend fun getOrder(): Response<List<OrderItem>>

    @PATCH("cancelorder")
    suspend fun patchOrder(
        @Body item: PatchcancelorderRequest
    ):Response<ApiResponse>

    @POST("login")
    suspend fun postLogin(
        @Body upass : LoginItem
    ):Response<LoginResponse>
}