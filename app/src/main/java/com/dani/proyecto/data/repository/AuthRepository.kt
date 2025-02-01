package com.dani.proyecto.data.repository

import com.dani.proyecto.data.model.LoginRequest
import com.dani.proyecto.data.model.LoginResponse
import com.dani.proyecto.data.network.ApiService
import retrofit2.Response

class AuthRepository(private val apiService: ApiService) {

    // Función para realizar el login
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return apiService.login(loginRequest)
    }

    // Función para registrar un nuevo usuario
    suspend fun register(registerRequest: LoginRequest): Response<LoginResponse> {
        return apiService.register(registerRequest)
    }
}
