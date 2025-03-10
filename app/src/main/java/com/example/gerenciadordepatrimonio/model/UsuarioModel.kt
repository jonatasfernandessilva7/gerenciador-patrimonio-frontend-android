package com.example.gerenciadordepatrimonio.model

data class Usuario(
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String?,
    val token: String
)