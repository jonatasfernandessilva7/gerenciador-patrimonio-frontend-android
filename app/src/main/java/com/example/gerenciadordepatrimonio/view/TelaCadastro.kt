package com.example.gerenciadordepatrimonio.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gerenciadordepatrimonio.viewmodel.CadastroViewModel

@Composable
fun TelaCadastro(cadastroViewModel: CadastroViewModel, navController: NavController){
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val cadastroResult by cadastroViewModel.cadastroResult.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = nome,
            onValueChange = {
                if (it.length <= 150) nome = it
            },
            label = { Text("Nome") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text, // Isso permite entrada de texto (String)
                imeAction = ImeAction.Done // Opcional, se você quiser controlar a ação do IME (teclado)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = {
                if (it.length <= 30) senha = it
            },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { cadastroViewModel.cadastro(nome, email, senha) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar")
        }

        cadastroResult?.let {
            navController.navigate("parabensVoceFoiCadastrado")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("telaLogin") {
                popUpTo("telaCadastro") { inclusive = true }
            } },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}