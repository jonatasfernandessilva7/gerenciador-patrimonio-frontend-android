package com.example.gerenciadordepatrimonio.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

// Modelo de usuário
data class Usuario(
    val id: Int?,
    val nome: String,
    val email: String,
    val senha: String?
)

data class Patrimonio(
    val id: Int,
    val nome: String,
    val descricao: String
)

data class LoginRequest(val email: String, val senha: String)
data class LoginResponse(val token: String, val user: Usuario, val success: Boolean)
data class CadastroRequest(val nome: String, val email: String, val senha: String)
data class CadastroResponse(val user: Usuario, val sucesso: Boolean)

interface ApiService {
    //usuario
    @POST("/criar")
    fun criarUsuario(@Body request: CadastroRequest): Call<CadastroResponse>

    @POST("/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("/perfil/{id}")
    fun obterPerfil(@Path("id") id: Int): Call<Usuario>

    //patrimonio

    @GET("/ver-todos-os-patrimonios")
    suspend fun getPatrimonios(@Header("Authorization") token: String): List<Patrimonio>
}