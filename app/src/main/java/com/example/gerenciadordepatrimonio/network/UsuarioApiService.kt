package com.example.gerenciadordepatrimonio.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.GET
import retrofit2.http.Path

// Modelo de usuário
data class Usuario(
    val id: Int?,
    val nome: String,
    val email: String,
    val senha: String?
)

data class LoginRequest(val email: String, val senha: String)
data class LoginResponse(val token: String, val user: Usuario)

interface ApiService {
    @POST("/criar")
    fun criarUsuario(@Body usuario: Usuario): Call<Usuario>

    @POST("/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("/perfil/{id}")
    fun obterPerfil(@Path("id") id: Int): Call<Usuario>
}