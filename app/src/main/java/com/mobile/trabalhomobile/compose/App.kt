package com.mobile.trabalhomobile.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()

    Scaffold(
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "homeScreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("homeScreen") {
                HomeScreen(navController = navController)
            }
            composable("jokeScreen") {
                JokeScreen(
                    onNavigateToHistory = { navController.navigate("historyScreen") },
                    onBackClick = { navController.popBackStack() },
                    onTechniquesClick = { navController.navigate("tecnicas_respiracao") },
                    onShareClick = { navController.navigate("homeScreen") }
                )
            }
            composable("historyScreen") {
                JokeHistoryScreen(onBackClick = { navController.popBackStack() })
            }
            composable("analisar_emocao") {
                AnalisarEmocaoScreen(navController = navController)
            }
            composable("dicas_estudo") {
                DicasEstudoScreen(onBack = { navController.popBackStack() })
            }
            composable("tecnicas_respiracao") {
                TecnicasRespiracaoScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}

