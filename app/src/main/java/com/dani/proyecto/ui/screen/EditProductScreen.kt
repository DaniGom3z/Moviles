package com.dani.proyecto.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dani.proyecto.viewmodel.MenuViewModel
import com.dani.proyecto.data.model.Product

@Composable
fun EditProductScreen(
    navController: NavController,
    menuViewModel: MenuViewModel,
    productId: Int
) {
    val products by menuViewModel.products.collectAsState()
    val product = products.find { it.id == productId }

    var name by remember { mutableStateOf(product?.name ?: "") }
    var quantity by remember { mutableStateOf(product?.quantity?.toString() ?: "") }
    var price by remember { mutableStateOf(product?.price?.toString() ?: "") }

    if (product == null) {
        Text("Producto no encontrado")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val updatedProduct = Product(
                    id = productId,
                    name = name,
                    quantity = quantity.toIntOrNull() ?: 0,
                    price = price.toFloatOrNull() ?: 0f
                )
                menuViewModel.updateProduct(productId, updatedProduct)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar Producto")
        }
    }
}
