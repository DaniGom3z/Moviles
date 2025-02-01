package com.dani.proyecto.data.model

data class LoginResponse(
    val token: String,
    val user: User
)

data class User(
    val id: Int,
    val email: String,
    val password:String
)