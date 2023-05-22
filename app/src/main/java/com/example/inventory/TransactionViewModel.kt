package com.example.inventory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TransactionViewModel : ViewModel() {
    private val _orderList = MutableLiveData<List<OrderItem2>>()
    val orderList: LiveData<List<OrderItem2>> get() = _orderList

    fun fetchOrder() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getOrder()

            if (response.isSuccessful) {
                response.body()?.let { orders ->
                    _orderList.value = orders
                }
            }
        }
    }

    fun changeOrderStatus(position: Int) {
        val orderListValue = _orderList.value.orEmpty().toMutableList()
        val order = orderListValue[position]
        order.is_promo = false
        _orderList.value = orderListValue
    }

//    try cancel Order
fun cancelOrder(sales_uname: String = "salesA", order_id: Int) {
    Log.d("Transaction Fragment", "Cancel Order Kepanggil")
    val filetoPatch = PatchcancelorderRequest(sales_uname, order_id)
    viewModelScope.launch {
        val response = RetrofitInstance.api.patchOrder(filetoPatch)
        if (response.isSuccessful) {
            Log.d("Transaction Fragment", response.body().toString())
            fetchOrder()
        }
    }
}
    //    try cancel Order
}
