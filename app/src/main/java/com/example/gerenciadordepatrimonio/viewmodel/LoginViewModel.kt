package com.example.gerenciadordepatrimonio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gerenciadordepatrimonio.model.Usuario
import com.example.gerenciadordepatrimonio.network.LoginRequest
import com.example.gerenciadordepatrimonio.network.LoginResponse
import com.example.gerenciadordepatrimonio.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

    private val _userId = MutableLiveData<String?>()
    val userId: LiveData<String?> = _userId

    private val _userData = MutableLiveData<Usuario?>()
    val userData: LiveData<Usuario?> = _userData

    fun login(email: String, senha: String) {
        val request = LoginRequest(email, senha)
        RetrofitClient.instance.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    _token.value = loginResponse?.token
                    _userId.value = loginResponse?.id.toString() // Armazena o ID do usuário
                    loginResponse?.let { fetchUserData(it.token, it.id.toString()) }
                } else {
                    _token.value = null
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _token.value = null
            }
        })
    }

    fun fetchUserData(token: String, userId: String) {
        RetrofitClient.instance.getUserData("Bearer $token", userId).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    _userData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                _userData.value = null
            }
        })
    }
}