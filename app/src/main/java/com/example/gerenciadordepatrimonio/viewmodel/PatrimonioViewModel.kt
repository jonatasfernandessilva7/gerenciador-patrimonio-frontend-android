package com.example.gerenciadordepatrimonio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gerenciadordepatrimonio.model.Patrimonio
import com.example.gerenciadordepatrimonio.repository.PatrimonioRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PatrimonioViewModel(private val usuarioId: Int) : ViewModel() {
    private val repository = PatrimonioRepository()

    // Armazenando a lista de patrimônios como um StateFlow
    val patrimonios: StateFlow<List<Patrimonio>> = repository.patrimonios
        .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.Lazily, emptyList())

    init {
        // Carregar os patrimônios do usuário
        viewModelScope.launch {
            repository.carregarPatrimonios(usuarioId)
        }
    }
}