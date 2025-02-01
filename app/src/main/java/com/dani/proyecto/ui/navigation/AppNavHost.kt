package com.dani.proyecto.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dani.proyecto.ui.screen.LoginScreen
import com.dani.proyecto.ui.screen.MenuScreen
import com.dani.proyecto.ui.screen.RegisterScreen
import com.dani.proyecto.viewmodel.AuthViewModel
import com.dani.proyecto.viewmodel.MenuViewModel
import com.dani.proyecto.ui.screen.EditProductScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel ,
    menuViewModel: MenuViewModel
) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable("register") {
            RegisterScreen(navController = navController,
                authViewModel = authViewModel,)

        }

        composable("menu") {
            // Pantalla de menú para el stock
            MenuScreen(
                navController = navController,
                productViewModel = menuViewModel
            )
        }
        composable(
            route = "edit/{productId}",
            arguments = listOf(navArgument("productId") { defaultValue = 0 })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            EditProductScreen(
                navController = navController,
                productId = productId,
                menuViewModel = menuViewModel
            )
        }
    }
}
