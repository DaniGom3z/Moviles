package com.dani.proyecto.data.model

data class ProductResponse(
    val success: Boolean,
    val message: String,
    val product: Product?
)
