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
import com.dani.proyecto.ui.navigation.AppNavHost
import com.dani.proyecto.viewmodel.MenuViewModel
import com.dani.proyecto.viewmodel.StockViewModelFactory

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(AuthRepository(RetrofitClient.apiService))
    }
    private val menuViewModel: MenuViewModel by viewModels {
        StockViewModelFactory(StockRepository(RetrofitClient.apiService))
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            AppNavHost(navController = navController, authViewModel = authViewModel, menuViewModel = menuViewModel)
        }
    }
}
