package com.example.gerenciadordepatrimonio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gerenciadordepatrimonio.network.CadastroRequest
import com.example.gerenciadordepatrimonio.network.CadastroResponse
import com.example.gerenciadordepatrimonio.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroViewModel : ViewModel() {
    private val _cadastroResult = MutableLiveData<CadastroResponse?>()
    val cadastroResult: LiveData<CadastroResponse?> = _cadastroResult

    fun cadastro(nome: String, email: String, senha: String) {
        val request = CadastroRequest(nome, email, senha)
        RetrofitClient.instance.criarUsuario(request).enqueue(object : Callback<CadastroResponse> {
            override fun onResponse(call: Call<CadastroResponse>, response: Response<CadastroResponse>) {
                if (response.isSuccessful) {
                    _cadastroResult.value = response.body()
                } else {
                    _cadastroResult.value = null
                }
            }

            override fun onFailure(call: Call<CadastroResponse>, t: Throwable) {
                _cadastroResult.value = null
            }
        })
    }
}