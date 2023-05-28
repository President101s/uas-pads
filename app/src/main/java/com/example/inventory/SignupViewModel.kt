package com.example.inventory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

    private val _regisStat = MutableLiveData<String>()
    val regisStat : LiveData<String> get() = _regisStat

    fun regis(username : String, password : String, name : String, email: String){
        viewModelScope.launch {
            val response = RetrofitInstance.api.postSignUp(SignupItem(username, password, name, email))

            if (response.isSuccessful){
                var status = response.body()?.status
                if (status == "success") {
                    Log.d(TAG, response.body().toString())
                    _regisStat.value = response.body()?.message
                }else {
                    _regisStat.value = response.body()?.message
                }
            }
        }
    }

}