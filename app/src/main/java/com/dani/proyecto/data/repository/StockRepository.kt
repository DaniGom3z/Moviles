package com.dani.proyecto.data.repository

import com.dani.proyecto.data.model.Product
import com.dani.proyecto.data.model.ProductResponse
import com.dani.proyecto.data.network.ApiService
import retrofit2.Response

class StockRepository(private val apiService: ApiService) {

    // Obtener todos los productos
    suspend fun getProducts(): Response<List<Product>> {
        return apiService.getProducts()
    }

    // Obtener un producto por su ID
    suspend fun getProductById(id: Int): Response<Product> {
        return apiService.getProduct(id)
    }

    // Agregar un nuevo producto
    suspend fun addProduct(product: Product): Response<ProductResponse> {
        return apiService.addProduct(product)
    }

    // Actualizar un producto existente
    suspend fun updateProduct(id: Int, product: Product): Response<ProductResponse> {
        return apiService.updateProduct(id, product)
    }

    // Eliminar un producto
    suspend fun deleteProduct(id: Int): Response<ProductResponse> {
        return apiService.deleteProduct(id)
    }
}
