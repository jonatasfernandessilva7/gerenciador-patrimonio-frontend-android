package com.example.gerenciadordepatrimonio.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.gerenciadordepatrimonio.model.Patrimonio
import retrofit2.Response

// Modelo de usuário
data class Usuario(
    val id: Int?,
    val nome: String,
    val email: String,
    val senha: String?
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

    // Patrimonio
    @GET("patrimonios/{usuarioId}")
    suspend fun getPatrimonios(@Path("usuarioId") usuarioId: Int): Response<List<Patrimonio>>
}