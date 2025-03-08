package com.example.gerenciadordepatrimonio.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gerenciadordepatrimonio.network.LoginRequest
import com.example.gerenciadordepatrimonio.network.LoginResponse
import com.example.gerenciadordepatrimonio.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<LoginResponse?>()
    val loginResult: LiveData<LoginResponse?> = _loginResult

    fun login(email: String, senha: String) {
        val request = LoginRequest(email, senha)
        RetrofitClient.instance.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    _loginResult.value = response.body()
                } else {
                    _loginResult.value = null
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _loginResult.value = null
            }
        })
    }
}