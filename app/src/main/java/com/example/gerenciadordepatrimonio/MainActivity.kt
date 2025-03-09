package com.example.gerenciadordepatrimonio

import TelaPatrimonios
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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

                    NavHost(navController = navController, startDestination = "telaInicio") {
                        composable(route = "telaInicio") {
                            TelaInicio(navController)
                        }
                        composable(route = "telaLogin") {
                            TelaLogin(loginViewModel = LoginViewModel(), navController)
                        }
                        composable(route = "telaPrincipal") {
                            TelaPrincipal(navController)
                        }
                        composable(route = "perfil") {
                            Perfil(navController,loginViewModel = LoginViewModel())
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
                        composable(
                            route = "telaPatrimonio"
                        ){
                            TelaPatrimonios(navController)
                        }
                    }
                }
            }
        }
    }
}