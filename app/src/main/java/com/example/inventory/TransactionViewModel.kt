package com.example.inventory

import androidx.lifecycle.ViewModel

class TransactionViewModel:ViewModel() {
    private val _orderList = mutableListOf<OrderItem>()
    val orderList: List<OrderItem> get() = _orderList
}