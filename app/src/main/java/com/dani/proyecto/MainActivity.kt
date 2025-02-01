package com.dani.proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.dani.proyecto.data.network.RetrofitClient
import com.dani.proyecto.data.repository.AuthRepository
import com.dani.proyecto.data.repository.StockRepository
import com.dani.proyecto.viewmodel.AuthViewModel
import com.dani.proyecto.viewmodel.AuthViewModelFactory
import com.dani.proyecto.ui.navigation.AppNavHost // Asegúrate de importar AppNavHost
import com.dani.proyecto.viewmodel.MenuViewModel
import com.dani.proyecto.viewmodel.StockViewModelFactory

class MainActivity : ComponentActivity() {

    // Obtener la instancia del AuthViewModel con el Factory
    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(AuthRepository(RetrofitClient.apiService))
    }
    // Obtener la instancia del AuthViewModel con el Factory
    private val menuViewModel: MenuViewModel by viewModels {
        StockViewModelFactory(StockRepository(RetrofitClient.apiService))
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Crea el NavController
            val navController = rememberNavController()

            // Pasa el navController y el authViewModel a AppNavHost
            AppNavHost(navController = navController, authViewModel = authViewModel, menuViewModel = menuViewModel)
        }
    }
}
