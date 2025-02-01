package com.dani.proyecto.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dani.proyecto.viewmodel.MenuViewModel
import com.dani.proyecto.data.model.Product

@Composable
fun MenuScreen(
    navController: NavController,
    productViewModel: MenuViewModel
) {
    var name by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val productList by productViewModel.products.collectAsState()

    LaunchedEffect(Unit) {
        productViewModel.fetchProducts()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Usar fondo del tema
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gestión de Productos",
            fontSize = 28.sp,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Añadir Producto",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        errorMessage = ""
                    },
                    label = { Text("Nombre del producto") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = name.isEmpty()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = quantity,
                    onValueChange = {
                        quantity = it
                        errorMessage = ""
                    },
                    label = { Text("Cantidad") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    isError = quantity.isEmpty()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = price,
                    onValueChange = {
                        price = it
                        errorMessage = ""
                    },
                    label = { Text("Precio") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    isError = price.isEmpty()
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Button(
                    onClick = {
                        if (name.isNotEmpty() && quantity.isNotEmpty() && price.isNotEmpty()) {
                            try {
                                val product = Product(
                                    name = name,
                                    quantity = quantity.toInt(),
                                    price = price.toFloat()
                                )
                                productViewModel.addProduct(product)
                                name = ""
                                quantity = ""
                                price = ""
                                errorMessage = ""
                            } catch (e: Exception) {
                                errorMessage = "Error al agregar producto: ${e.message}"
                                Log.e("MenuScreen", errorMessage)
                            }
                        } else {
                            errorMessage = "Todos los campos son obligatorios"
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Agregar Producto", color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Lista de productos
        Text(
            text = "Lista de Productos",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        productList.forEach { product ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Nombre: ${product.name}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Cantidad: ${product.quantity}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Precio: $${product.price}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = {  navController.navigate("edit/${product.id}")}) {
                            Text("Editar", color = MaterialTheme.colorScheme.primary)
                        }
                        TextButton(onClick = { productViewModel.deleteProduct(product.id) }) {
                            Text("Eliminar", color = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }
}
