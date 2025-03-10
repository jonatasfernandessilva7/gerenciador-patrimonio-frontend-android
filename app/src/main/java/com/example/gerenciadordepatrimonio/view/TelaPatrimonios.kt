package com.example.gerenciadordepatrimonio.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gerenciadordepatrimonio.model.PatrimonioResponse
import com.example.gerenciadordepatrimonio.network.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaPatrimonios(navController: NavHostController) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PatrimoniosScreen()
        }
    }
}

@Composable
fun PatrimoniosScreen() {
    val patrimonioList = remember { mutableStateListOf<PatrimonioResponse>() }
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Recupera o ID do usuário logado armazenado no SharedPreferences
    val userId = getUserIdFromStorage()

    LaunchedEffect(Unit) {
        if (userId != null) {
            coroutineScope.launch {
                RetrofitClient.instance.getPatrimonios(userId).enqueue(object : Callback<List<PatrimonioResponse>> {
                    override fun onResponse(call: Call<List<PatrimonioResponse>>, response: Response<List<PatrimonioResponse>>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                patrimonioList.addAll(it) // Adiciona a lista de patrimônios
                            }
                            isLoading = false
                        } else {
                            errorMessage = "Erro ao carregar patrimônios"
                            isLoading = false
                        }
                    }

                    override fun onFailure(call: Call<List<PatrimonioResponse>>, t: Throwable) {
                        errorMessage = "Falha na conexão"
                        isLoading = false
                        Log.e("API_ERROR", t.message ?: "Erro desconhecido")
                    }
                })
            }
        } else {
            errorMessage = "ID do usuário não encontrado"
            isLoading = false
        }
    }

    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    } else if (errorMessage != null) {
        Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn {
            items(patrimonioList) { patrimonio ->
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Patrimônio ID: ${patrimonio.patrimonio.id}") // Ajuste para mostrar o ID
                    }
                }
            }
        }
    }
}

@Composable
fun getUserIdFromStorage(): Int? {
    // Acessa o contexto atual
    val context = LocalContext.current

    // Acessa o SharedPreferences
    val sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

    // Retorna o ID do usuário ou null caso não encontrado
    return sharedPreferences.getInt("user_id", -1).takeIf { it != -1 }
}