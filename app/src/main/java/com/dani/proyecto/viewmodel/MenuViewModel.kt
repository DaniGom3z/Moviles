package com.dani.proyecto.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dani.proyecto.data.model.Product
import com.dani.proyecto.data.repository.StockRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class StockState {
    data object Loading : StockState()
    data class Error(val message: String) : StockState()
    data object ProductAdded : StockState()
    data object ProductUpdated : StockState()
    data object ProductDeleted : StockState()
}

class MenuViewModel(private val stockRepository: StockRepository) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _stockState = MutableStateFlow<StockState?>(null)

    // Obtener todos los productos
    fun fetchProducts() {
        _stockState.value = StockState.Loading
        viewModelScope.launch {
            try {
                val response = stockRepository.getProducts()
                if (response.isSuccessful) {
                    val products = response.body() ?: emptyList()
                    _products.value = products
                    Log.d("MenuViewModel", "Productos obtenidos: $products")
                } else {
                    _stockState.value = StockState.Error("Error al obtener productos")
                }
            } catch (e: Exception) {
                _stockState.value = StockState.Error("Error en la conexión: ${e.message}")
            }
        }
    }

    // Agregar producto
    fun addProduct(product: Product) {
        _stockState.value = StockState.Loading
        viewModelScope.launch {
            try {
                val response = stockRepository.addProduct(product)
                if (response.isSuccessful) {
                    _stockState.value = StockState.ProductAdded
                    fetchProducts() // Actualizar lista de productos
                } else {
                    _stockState.value = StockState.Error("Error al agregar producto")
                }
            } catch (e: Exception) {
                _stockState.value = StockState.Error("Error en la conexión: ${e.message}")
            }
        }
    }

    // Eliminar producto
    fun deleteProduct(productId: Int) {
        _stockState.value = StockState.Loading
        viewModelScope.launch {
            try {
                val response = stockRepository.deleteProduct(productId)
                if (response.isSuccessful) {
                    _stockState.value = StockState.ProductDeleted
                    fetchProducts()
                } else {
                    _stockState.value = StockState.Error("Error al eliminar producto")
                }
            } catch (e: Exception) {
                _stockState.value = StockState.Error("Error en la conexión: ${e.message}")
            }
        }
    }

    fun updateProduct(productId: Int, updatedProduct: Product) {
        _stockState.value = StockState.Loading
        viewModelScope.launch {
            try {
                val response = stockRepository.updateProduct(productId, updatedProduct)
                if (response.isSuccessful) {
                    _stockState.value = StockState.ProductUpdated
                    fetchProducts()
                } else {
                    _stockState.value = StockState.Error("Error al actualizar el producto")
                }
            } catch (e: Exception) {
                _stockState.value = StockState.Error("Error en la conexión: ${e.message}")
            }
        }
    }

}
