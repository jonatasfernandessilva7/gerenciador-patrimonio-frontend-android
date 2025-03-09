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
            PatrimoniosScreen<Any>()
        }
    }
}

@Composable
fun <Patrimonio> PatrimoniosScreen() {
    val patrimonioList = remember { mutableStateListOf<PatrimonioResponse>() }
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val token = "Bearer " + getTokenFromStorage()
            RetrofitClient.instance.getPatrimonios(token).enqueue(object : Callback<List<PatrimonioResponse>> { // Corrigido: Callback com lista
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
    }

    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    } else if (errorMessage != null) {
        Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn {
            items(patrimonioList) { patrimonio -> // Corrigido: Usa PatrimonioResponse
                Card(
                    // ...
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Corrigido: CardElevation
                ) {
                    Column {
                        // ... (acessar dados com patrimonio.patrimonio.bens etc.)
                        Text(text = "Bens: ${patrimonio.patrimonio.bens.imoveis.size} Imóveis, ${patrimonio.patrimonio.bens.veiculos.size} Veículos")
                        Text(text = "Direitos: ${patrimonio.patrimonio.direitos.size}")
                        Text(text = "Obrigações: ${patrimonio.patrimonio.obrigacoes.size}")
                    }
                }
            }
        }
    }
}

// Simulação de função para obter token do SharedPreferences
fun getTokenFromStorage(): String {
    return "seu_token_aqui" // Aqui você deve buscar o token real do SharedPreferences
}