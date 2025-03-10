package com.example.gerenciadordepatrimonio.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.gerenciadordepatrimonio.viewmodel.LoginViewModel

@Composable
fun Perfil(navController: NavController, loginViewModel: LoginViewModel) {
    val userData by loginViewModel.userData.observeAsState()
    val userId by loginViewModel.userId.observeAsState()
    val token by loginViewModel.token.observeAsState()

    var isEditing by rememberSaveable { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("******") }

    // Busca os dados do usuário quando a tela abrir
    LaunchedEffect(userId, token) {
        if (userId != null && token != null) {
            loginViewModel.fetchUserData(token!!, userId!!)
        }
    }

    // Atualiza os campos quando os dados forem carregados
    LaunchedEffect(userData) {
        userData?.let {
            name = it.nome
            email = it.email
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Gerenciador de Patrimônios", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        ProfileTextField(label = "Nome", value = name, onValueChange = { name = it }, enabled = isEditing)
        ProfileTextField(label = "E-mail", value = email, onValueChange = { email = it }, enabled = isEditing)
        ProfileTextField(label = "Senha", value = password, onValueChange = { password = it }, enabled = isEditing, isPassword = true)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isEditing) {
                // Botões de "Cancelar" e "Salvar" aparecem quando "Mudar Dados" é clicado
                Button(onClick = { isEditing = false }) {
                    Text("Cancelar")
                }

                Button(onClick = {
                    isEditing = false
                    // Aqui você pode chamar a função para salvar os dados no backend
                }) {
                    Text("Salvar")
                }
            } else {
                // Botões padrão quando não está no modo de edição
                Button(onClick = { isEditing = true }) {
                    Text("Alterar Dados")
                }

                Button(onClick = { navController.navigate("telaPrincipal") }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                }
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

@Composable
fun ProfileTextField(label: String, value: String, onValueChange: (String) -> Unit, enabled: Boolean, isPassword: Boolean = false) {
    Column {
        Text(label, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier.fillMaxWidth()
        )
    }
}