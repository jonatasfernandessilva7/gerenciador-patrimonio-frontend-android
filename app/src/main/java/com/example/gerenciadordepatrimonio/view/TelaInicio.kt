package com.example.gerenciadordepatrimonio.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gerenciadordepatrimonio.viewmodel.PatrimonioViewModel
import com.example.gerenciadordepatrimonio.model.Patrimonio
import androidx.compose.ui.Modifier

@Composable
fun TelaInicio(navController: NavController, viewModel: PatrimonioViewModel) {
    val patrimonios by viewModel.patrimonios.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Bem-vindo a tela principal!",
                modifier = Modifier.padding(16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Lista de Patrimônios
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            items(patrimonios) { patrimonio ->
                PatrimonioItem(patrimonio)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botões de ação
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { /** Navegar para ver patrimônio */ }) {
                Icon(Icons.Filled.List, contentDescription = "Ver Todo O Patrimônio")
            }
            IconButton(onClick = { /** Navegar para inserir patrimônio */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Inserir Novo Patrimônio")
            }
            IconButton(onClick = { /** Navegar para deletar patrimônio */ }) {
                Icon(Icons.Filled.Delete, contentDescription = "Deletar Patrimônio")
            }
            IconButton(onClick = { /** Navegar para atualizar patrimônio */ }) {
                Icon(Icons.Filled.Edit, contentDescription = "Atualizar Patrimônio")
            }
        }
    }
}

@Composable
fun PatrimonioItem(patrimonio: Patrimonio) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nome: ${patrimonio.nome}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Valor: R$ ${patrimonio.valor}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}