package com.example.gerenciadordepatrimonio.repository

import com.example.gerenciadordepatrimonio.model.Patrimonio
import com.example.gerenciadordepatrimonio.network.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class PatrimonioRepository {
    private val _patrimonios = MutableStateFlow<List<Patrimonio>>(emptyList())
    val patrimonios: StateFlow<List<Patrimonio>> get() = _patrimonios

    suspend fun carregarPatrimonios(usuarioId: Int) {
        try {
            val response = RetrofitClient.instance.getPatrimonios(usuarioId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _patrimonios.value = it
                }
            } else {
                // Se a resposta não for bem-sucedida, podemos lidar com isso aqui
                _patrimonios.value = emptyList()
            }
        } catch (e: Exception) {
            // Caso haja erro de rede ou outra exceção
            _patrimonios.value = emptyList()
        }
    }
}