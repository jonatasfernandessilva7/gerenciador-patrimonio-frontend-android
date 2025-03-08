package com.example.gerenciadordepatrimonio

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
import com.example.gerenciadordepatrimonio.view.EsqueciSenha
import com.example.gerenciadordepatrimonio.view.ParabensVoceFoiCadastrado
import com.example.gerenciadordepatrimonio.view.Perfil
import com.example.gerenciadordepatrimonio.view.TelaCadastro
import com.example.gerenciadordepatrimonio.view.TelaInicio
import com.example.gerenciadordepatrimonio.view.TelaLogin
import com.example.gerenciadordepatrimonio.viewmodel.CadastroViewModel
import com.example.gerenciadordepatrimonio.viewmodel.LoginViewModel

@Suppress("UNREACHABLE_CODE")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GerenciadorDePatrimonioTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "telaLogin") {
                        composable(
                            route = "telaLogin"
                        ){
                            TelaLogin(loginViewModel = LoginViewModel(), navController)
                        }
                        composable(
                            route = "telaInicio"
                        ) {
                            TelaInicio(
                                navController,
                                context = TODO()
                            )
                        }
                        composable(
                            route = "perfil"
                        ){
                            Perfil(navController)
                        }
                        composable(
                            route = "telaCadastro"
                        ){
                            TelaCadastro(cadastroViewModel = CadastroViewModel() ,navController)
                        }
                        composable(
                            route = "parabensVoceFoiCadastrado"
                        ){
                            ParabensVoceFoiCadastrado(navController)
                        }
                        composable(
                            route = "esqueciASenha"
                        ){
                            EsqueciSenha(navController)
                        }
                    }
                }
            }
        }
    }
}

