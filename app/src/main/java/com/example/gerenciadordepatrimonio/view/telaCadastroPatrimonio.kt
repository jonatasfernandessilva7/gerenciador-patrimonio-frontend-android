package com.example.gerenciadordepatrimonio.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun TelaCadastroPatrimonio(navController: NavController) {

    var isSelected by rememberSaveable { mutableStateOf(false) }

    // Estado para armazenar a seleção do tipo de patrimônio (Bem, Direito ou Obrigação)
    val selectedType = remember { mutableStateOf<String?>(null) }

    // Estado para o tipo de bem (Imóvel, Terreno, Veículo, etc.)
    val selectedBemType = remember { mutableStateOf<String?>(null) }

    // Estados para os campos de entrada
    val nome = remember { mutableStateOf("") }
    val descricao = remember { mutableStateOf("") }
    val valor = remember { mutableStateOf("") }
    val imovelDescricao = remember { mutableStateOf("") }
    val terrenoLocalizacao = remember { mutableStateOf("") }
    val veiculoModelo = remember { mutableStateOf("") }
    val participacaoEmpresa = remember { mutableStateOf("") }
    val outroInvestimentoTipo = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Título
        Text(text = "Cadastro de Patrimônio", style = MaterialTheme.typography.headlineLarge)

        // Selecione o tipo de patrimônio
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Escolha o tipo de Patrimônio:", style = MaterialTheme.typography.titleMedium)


        // Botões de escolha para o tipo de patrimônio
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { selectedType.value = "Bem" },
                modifier = Modifier.widthIn(100.dp)
            ) {
                Text(text = "Bem")
            }
            Button(
                onClick = { selectedType.value = "Direito" },
                modifier = Modifier.widthIn(100.dp)
            ) {
                Text(text = "Direito")
            }
            Button(
                onClick = { selectedType.value = "Obrigação" },
                modifier = Modifier.widthIn(100.dp)
            ) {
                Text(text = "Obrigação")
            }
        }

        // Se o tipo de patrimônio for Bem, exibe a escolha do tipo de bem
        Spacer(modifier = Modifier.height(16.dp))
        if (selectedType.value == "Bem") {
            Text(text = "Escolha o tipo de Bem:", style = MaterialTheme.typography.titleMedium)
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { selectedBemType.value = "Imovel" },
                    modifier = Modifier.widthIn(80.dp)
                ) {
                    Text(text = "Imóvel")
                }
                Button(
                    onClick = { selectedBemType.value = "Terreno" },
                    modifier = Modifier.widthIn(80.dp)
                ) {
                    Text(text = "Terreno")
                }
                Button(
                    onClick = { selectedBemType.value = "Veiculo" },
                    modifier = Modifier.widthIn(80.dp)
                ) {
                    Text(text = "Veículo")
                }
                Button(
                    onClick = { selectedBemType.value = "Participacao" },
                    modifier = Modifier.widthIn(80.dp)
                ) {
                    Text(text = "Participação")
                }
                Button(
                    onClick = { selectedBemType.value = "OutroInvestimento" },
                    modifier = Modifier.widthIn(80.dp)
                ) {
                    Text(text = "Outro Investimento")
                }
            }
        }

        // Exibe os campos específicos dependendo da escolha do tipo de bem
        Spacer(modifier = Modifier.height(16.dp))
        when (selectedBemType.value) {
            "Imovel" -> {
                Text(text = "Cadastro de Imóvel", style = MaterialTheme.typography.titleMedium)
                ImovelForm(imovelDescricao = imovelDescricao)
            }
            "Terreno" -> {
                Text(text = "Cadastro de Terreno", style = MaterialTheme.typography.titleMedium)
                TerrenoForm(terrenoLocalizacao = terrenoLocalizacao)
            }
            "Veiculo" -> {
                Text(text = "Cadastro de Veículo", style = MaterialTheme.typography.titleMedium)
                VeiculoForm(veiculoModelo = veiculoModelo)
            }
            "Participacao" -> {
                Text(text = "Cadastro de Participação", style = MaterialTheme.typography.titleMedium)
                ParticipacaoForm(participacaoEmpresa = participacaoEmpresa)
            }
            "OutroInvestimento" -> {
                Text(text = "Cadastro de Outro Investimento", style = MaterialTheme.typography.titleMedium)
                OutroInvestimentoForm(outroInvestimentoTipo = outroInvestimentoTipo)
            }
            else -> {
                // Nenhum tipo de bem selecionado
                Text(text = "Selecione um tipo de bem para continuar.")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        when(selectedType.value){
            "Direito" -> {
                Text(text = "Cadastro de Direito", style = MaterialTheme.typography.titleMedium)
                DireitoForm(nome = nome, descricao = descricao, valor = valor)
            }
            "Obrigacao" -> {
                Text(text = "Cadastro de Obrigação", style = MaterialTheme.typography.titleMedium)
            ObrigacaoForm(nome = nome, descricao = descricao, valor = valor)
            }
        }

        if (selectedBemType.value != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Ação de cadastro aqui
                    cadastrarPatrimonio()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Cadastrar Patrimônio")
            }
        }

        // Botões de navegação
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { navController.navigate("telaPrincipal") }) {
                Icon(Icons.Filled.Home, contentDescription = "Voltar Para Tela Principal")
            }
            IconButton(onClick = { navController.navigate("telaPatrimonio") }) {
                Icon(Icons.Filled.List, contentDescription = "Ver Todos Os Patrimônios")
            }
            IconButton(onClick = { navController.navigate("perfil") }) {
                Icon(Icons.Filled.Person, contentDescription = "Tela De Perfil")
            }
        }
    }
}


fun cadastrarPatrimonio() {
    println("Cadastro de Patrimônio realizado com sucesso!")
}

// Formulários específicos para cada tipo de bem
@Composable
fun ImovelForm(imovelDescricao: MutableState<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = imovelDescricao.value,
            onValueChange = { imovelDescricao.value = it },
            label = { Text(text = "Descrição do Imóvel") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
    }
}

@Composable
fun TerrenoForm(terrenoLocalizacao: MutableState<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = terrenoLocalizacao.value,
            onValueChange = { terrenoLocalizacao.value = it },
            label = { Text(text = "Localização do Terreno") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
    }
}

@Composable
fun VeiculoForm(veiculoModelo: MutableState<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = veiculoModelo.value,
            onValueChange = { veiculoModelo.value = it },
            label = { Text(text = "Modelo do Veículo") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
    }
}

@Composable
fun ParticipacaoForm(participacaoEmpresa: MutableState<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = participacaoEmpresa.value,
            onValueChange = { participacaoEmpresa.value = it },
            label = { Text(text = "Nome da Empresa") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
    }
}

@Composable
fun OutroInvestimentoForm(outroInvestimentoTipo: MutableState<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = outroInvestimentoTipo.value,
            onValueChange = { outroInvestimentoTipo.value = it },
            label = { Text(text = "Tipo de Investimento") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
    }
}

@Composable
fun DireitoForm(nome: MutableState<String>, descricao: MutableState<String>, valor: MutableState<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = nome.value,
            onValueChange = { nome.value = it },
            label = { Text(text = "Nome do Patrimônio") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        // Campo para Descrição
        TextField(
            value = descricao.value,
            onValueChange = { descricao.value = it },
            label = { Text(text = "Descrição") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        // Campo para Valor
        TextField(
            value = valor.value,
            onValueChange = { valor.value = it },
            label = { Text(text = "Valor") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

    }
}

@Composable
fun ObrigacaoForm(nome: MutableState<String>, descricao: MutableState<String>, valor: MutableState<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = nome.value,
            onValueChange = { nome.value = it },
            label = { Text(text = "Nome do Patrimônio") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        // Campo para Descrição
        TextField(
            value = descricao.value,
            onValueChange = { descricao.value = it },
            label = { Text(text = "Descrição") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        // Campo para Valor
        TextField(
            value = valor.value,
            onValueChange = { valor.value = it },
            label = { Text(text = "Valor") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaCadastroPatrimonio() {
    TelaCadastroPatrimonio(navController = NavController(context = LocalContext.current)) // Para preview, você pode passar um NavController simulado
}