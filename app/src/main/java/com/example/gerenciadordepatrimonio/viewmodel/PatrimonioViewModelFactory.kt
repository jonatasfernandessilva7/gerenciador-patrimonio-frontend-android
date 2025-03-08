package com.example.gerenciadordepatrimonio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PatrimonioViewModelFactory(private val usuarioId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatrimonioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PatrimonioViewModel(usuarioId) as T
        }
        throw IllegalArgumentException("ViewModel Desconhecida")
    }
}