package com.dani.proyecto.data.network

import com.dani.proyecto.data.model.LoginRequest
import com.dani.proyecto.data.model.LoginResponse
import com.dani.proyecto.data.model.Product
import com.dani.proyecto.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    // Registro de usuario
    @POST("register")
    suspend fun register(@Body registerRequest: LoginRequest): Response<LoginResponse>

    // Crear un nuevo producto
    @POST("stock")
    suspend fun addProduct(@Body product: Product): Response<ProductResponse>

    // Obtener todos los productos
    @GET("stock")
    suspend fun getProducts(): Response<List<Product>>

    // Obtener un producto por su ID
    @GET("stock/{id}")
    suspend fun getProduct(@Path("id") id: Int): Response<Product>

    // Actualizar un producto
    @PUT("stock/{id}")
    suspend fun updateProduct(@Path("id") id: Int, @Body product: Product): Response<ProductResponse>

    // Eliminar un producto
    @DELETE("stock/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<ProductResponse>
}
