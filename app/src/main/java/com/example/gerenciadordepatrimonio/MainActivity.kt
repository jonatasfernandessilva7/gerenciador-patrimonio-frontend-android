package com.example.gerenciadordepatrimonio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gerenciadordepatrimonio.ui.theme.GerenciadorDePatrimonioTheme
import com.example.gerenciadordepatrimonio.view.Perfil
import com.example.gerenciadordepatrimonio.view.TelaInicio
import com.example.gerenciadordepatrimonio.view.TelaLogin
import com.example.gerenciadordepatrimonio.viewmodel.LoginViewModel

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
                        ){
                            TelaInicio(navController)
                        }
                        composable(
                            route = "perfil"
                        ){
                            Perfil(navController)
                        }
                    }
                }
            }
        }
    }
}

