package com.example.gerenciadordepatrimonio.network

import com.example.gerenciadordepatrimonio.model.PatrimonioResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Header
import com.example.gerenciadordepatrimonio.model.Usuario

data class LoginRequest(val email: String, val senha: String)
data class LoginResponse(val token: String, val user: Usuario, val success: Boolean, val id: Int)

data class UserResponse(
    val id: Int,
    val nome: String,
    val email: String
)

data class CadastroRequest(val nome: String, val email: String, val senha: String)
data class CadastroResponse(val user: Usuario, val sucesso: Boolean)


interface ApiService {
    // Cadastro/Login
    @POST("criar")
    fun criarUsuario(@Body request: CadastroRequest): Call<CadastroResponse>

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("perfil/{id}")
    fun getUserData(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<UserResponse>

    @GET("patrimonios")
    fun getPatrimonios(@Header("Authorization") token: String): Call<List<PatrimonioResponse>>
}