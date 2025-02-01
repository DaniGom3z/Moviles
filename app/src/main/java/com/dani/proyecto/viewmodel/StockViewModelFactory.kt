package com.dani.proyecto.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dani.proyecto.data.repository.StockRepository

class StockViewModelFactory(
    private val stockRepository: StockRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            MenuViewModel(stockRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
