package com.example.gerenciadordepatrimonio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gerenciadordepatrimonio.repository.PatrimonioRepository

class TelaInicioViewModelFactory(private val patrimonioRepository: PatrimonioRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TelaInicioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TelaInicioViewModel(patrimonioRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}