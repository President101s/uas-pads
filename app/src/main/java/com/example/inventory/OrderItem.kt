package com.example.inventory

import com.google.gson.annotations.SerializedName

data class OrderItem(
    val id : Int,
    val date : String,
    val status : String,
    val total_price : Int,
    val qty : Int,
    val sales_id : String,
    val customer_id : String
)

data class PatchcancelorderRequest(
    @SerializedName("sales_username") val sales_username: String,
    @SerializedName("order_id") val order_id: Int,
)

data class ApiResponse(
    val message: String,
    val status: String
)
