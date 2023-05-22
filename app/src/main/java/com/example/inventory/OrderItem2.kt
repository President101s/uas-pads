package com.example.inventory

data class OrderItem2(
    val available_qty: Int,
    val id: Int,
    val img_link: String,
    var is_promo: Boolean,
    val name: String,
    val ordered_qty: Int,
    val price: Int,
    val total_qty: Int,
    val warehouse_id: Int,
    val description: String,
    val category_name: String
)
