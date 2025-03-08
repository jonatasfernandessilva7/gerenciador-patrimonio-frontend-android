package com.example.gerenciadordepatrimonio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gerenciadordepatrimonio.network.Patrimonio
import com.example.gerenciadordepatrimonio.network.RetrofitClient
import com.example.gerenciadordepatrimonio.repository.PatrimonioRepository
import kotlinx.coroutines.launch

class TelaInicioViewModel(private val patrimonioRepository: PatrimonioRepository) : ViewModel() {
    private val _patrimonios = MutableLiveData<List<Patrimonio>>()
    val patrimonios: LiveData<List<Patrimonio>> = _patrimonios

    fun carregarPatrimonios() {
        val token = patrimonioRepository.getToken()
        if (token != null) {
            viewModelScope.launch {
                try {
                    val response = RetrofitClient.instance.getPatrimonios("Bearer $token")
                    _patrimonios.postValue(response)
                } catch (e: Exception) {
                    _patrimonios.postValue(emptyList())
                }
            }
        }
    }
}