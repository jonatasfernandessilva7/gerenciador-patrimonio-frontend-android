package com.example.gerenciadordepatrimonio.view

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gerenciadordepatrimonio.network.Patrimonio
import com.example.gerenciadordepatrimonio.repository.PatrimonioRepository
import com.example.gerenciadordepatrimonio.viewmodel.TelaInicioViewModel
import com.example.gerenciadordepatrimonio.viewmodel.TelaInicioViewModelFactory

@Composable
fun TelaInicio(
    navController: NavController,
    context: Context
) {
    // Criar o repositório e passar para a ViewModel
    val patrimonioRepository = PatrimonioRepository(context)
    val viewModel: TelaInicioViewModel = viewModel(factory = TelaInicioViewModelFactory(patrimonioRepository))

    // Obter os patrimonios observados pela ViewModel
    val patrimonios by viewModel.patrimonios.observeAsState(initial = emptyList())

    // Carregar os patrimonios apenas uma vez
    LaunchedEffect(key1 = true) {
        viewModel.carregarPatrimonios()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Meus Patrimônios", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        if (patrimonios.isEmpty()) {
            Text("Nenhum patrimônio encontrado.", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn {
                items(patrimonios) { patrimonio ->
                    PatrimonioItem(patrimonio)
                }
            }
        }
    }
}

@Composable
fun PatrimonioItem(patrimonio: Patrimonio) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = patrimonio.nome, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = patrimonio.descricao, style = MaterialTheme.typography.bodyMedium)
        }
    }
}