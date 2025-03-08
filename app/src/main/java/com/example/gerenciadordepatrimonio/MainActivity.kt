package com.example.gerenciadordepatrimonio

import android.os.Bundle
import android.content.Context
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gerenciadordepatrimonio.ui.theme.GerenciadorDePatrimonioTheme
import com.example.gerenciadordepatrimonio.view.*
import com.example.gerenciadordepatrimonio.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GerenciadorDePatrimonioTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    // Recupera o usuarioId salvo no SharedPreferences (ajuste conforme necessário)
                    val usuarioId = recuperarUsuarioId(this)

                    NavHost(navController = navController, startDestination = "telaLogin") {
                        composable(route = "telaLogin") {
                            TelaLogin(loginViewModel = LoginViewModel(), navController)
                        }
                        composable(route = "telaInicio") {
                            val viewModel: PatrimonioViewModel = ViewModelProvider(
                                this@MainActivity,
                                PatrimonioViewModelFactory(usuarioId)
                            ).get(PatrimonioViewModel::class.java)

                            TelaInicio(navController, viewModel)
                        }
                        composable(route = "perfil") {
                            Perfil(navController)
                        }
                        composable(route = "telaCadastro") {
                            TelaCadastro(cadastroViewModel = CadastroViewModel(), navController)
                        }
                        composable(route = "parabensVoceFoiCadastrado") {
                            ParabensVoceFoiCadastrado(navController)
                        }
                        composable(route = "esqueciASenha") {
                            EsqueciSenha(navController)
                        }
                    }
                }
            }
        }
    }

    // Função para recuperar usuarioId do SharedPreferences
    private fun recuperarUsuarioId(context: Context): Int {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("usuarioId", -1) // Retorna -1 se não encontrar
    }
}