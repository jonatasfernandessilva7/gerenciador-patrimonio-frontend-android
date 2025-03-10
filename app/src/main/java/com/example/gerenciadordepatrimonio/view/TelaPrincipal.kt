package com.example.gerenciadordepatrimonio.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Modifier

@Composable
fun TelaPrincipal(navController: NavController) {

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

        Text(text = "Créditos:")

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Robert Michael Ávila, desenvolvedor full-stack" +
                        "Github: https://github.com/Robertmichaelavila" +
                        "Instagram: https://www.instagram.com/robert_mchael/",
                modifier = Modifier.padding(16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                "Jônatas Fernandes, desenvolvedor full-stack" +
                        "Github: https://github.com/jonatasfernandessilva7" +
                        "Instagram: https://www.instagram.com/_jonatas.fernandes_/",
                modifier = Modifier.padding(16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                "Gustavo Menezes, Designer e BDM" +
                        "Github: https://github.com/Gustavo123123123123" +
                        "Instagram: https://www.instagram.com/1_forro_de_2/",
                modifier = Modifier.padding(16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botões de ação
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { navController.navigate("telaPrincipal") }) {
                Icon(Icons.Filled.Home, contentDescription = "Voltar A Tela Principal")
            }
            IconButton(onClick = { navController.navigate("telaPatrimonio") }) {
                Icon(Icons.Filled.List, contentDescription = "Ver Todo O Patrimônio")
            }
            IconButton(onClick = { navController.navigate("telaCadastroPatrimonio") }) {
                Icon(Icons.Filled.Add, contentDescription = "Adicionar Patrimônio")
            }
            IconButton(onClick = { navController.navigate("perfil") }) {
                Icon(Icons.Filled.Person, contentDescription = "Tela De Perfil")
            }
        }
    }
}