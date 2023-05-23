package com.example.inventory

import com.google.gson.annotations.SerializedName


data class LoginItem(
    @SerializedName("password") val password: String,
    @SerializedName("username") val username: String
)

data class LoginResponse(
    val sales_username: String,
)