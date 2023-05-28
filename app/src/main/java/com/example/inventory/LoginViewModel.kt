package com.example.inventory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    private val _verificationStatus = MutableLiveData<Boolean>()
    val verificationStatus: LiveData<Boolean> get() = _verificationStatus

    fun postlogin(salesUsername : String, salesPassword : String){


        viewModelScope.launch {
            val response = RetrofitInstance.api.postLogin(LoginItem(salesUsername, salesPassword))


            if (response.isSuccessful){
                var sales_username = response.body()?.sales_username
                if (sales_username != null) {
                    Log.d(TAG, response.body().toString())
                    _verificationStatus.value = true
                }else {
                    _verificationStatus.value = false
                }
            }
        }
//return true so dari fragment utama bisa confirm kalau password and user itu sah dan bisa navigate ke tmpt lain
    }
}