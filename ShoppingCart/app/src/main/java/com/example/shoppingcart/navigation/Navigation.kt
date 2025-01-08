package com.example.shoppingcart.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shoppingcart.ui.presentation.cart.CartScreen
import com.example.shoppingcart.ui.presentation.home.HomeScreen
import com.example.shoppingcart.ui.presentation.login.LoginScreen
import com.example.shoppingcart.ui.presentation.register.RegisterScreen

@Composable
fun NavigateApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login"){
        composable(route = "login")
        {
            LoginScreen(navController = navController)
        }

        composable(route = "register")
        {
            RegisterScreen(navController = navController)
        }

        composable(
            route = "home/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ){
                backStackEntry ->
            HomeScreen(navController = navController, userId = backStackEntry.arguments?.getString("userId"))
        }

        composable(
            route = "cart/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            CartScreen(navController = navController, userId = backStackEntry.arguments?.getString("userId") ?: "")
        }
    }
}