package com.example.gerenciadordepatrimonio.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gerenciadordepatrimonio.R

@Composable
fun TelaInicio(navController: NavController
){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Logo
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                tint = Color.Unspecified,
                modifier = Modifier.size(120.dp)
            )


            // Botão "Entrar"
            Button(
                onClick = { navController.navigate("telaLogin") {
                    popUpTo("telaInicio") { inclusive = true }
                } },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0E5A75) // Azul semelhante ao da imagem
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Avançar", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}