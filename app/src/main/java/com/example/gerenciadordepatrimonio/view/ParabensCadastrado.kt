package com.example.gerenciadordepatrimonio.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ParabensVoceFoiCadastrado(navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Parabéns, Você Está Cadastrado!!")
    }

    Button(
        onClick = { navController.navigate("telaLogin") {
            popUpTo("parabensVoceFoiCadastrado") { inclusive = true }
        } },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Voltar")
    }

}