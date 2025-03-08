package com.example.gerenciadordepatrimonio.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController

@Composable
fun ParabensVoceFoiCadastrado(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Texto de Parabéns estilizado
        Text(
            text = "Parabéns, Você Está Cadastrado!!",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                color = Color(0xFF6200EE)  // Cor roxa para destacar
            ),
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF6200EE) // Cor roxa
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de Voltar
        Button(
            onClick = {
                navController.navigate("telaLogin") {
                    popUpTo("parabensVoceFoiCadastrado") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("OK")
        }
    }
}