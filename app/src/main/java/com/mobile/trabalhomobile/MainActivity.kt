package com.mobile.trabalhomobile

import AnalisarEmocaoScreen
import JokeHistoryScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onPreInterceptKeyBeforeSoftKeyboard
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aa1.compose.DicasEstudoScreen
import com.example.aa1.compose.HomeScreen
import com.example.aa1.compose.TecnicasRespiracaoScreen
import com.mobile.trabalhomobile.ui.theme.TrabalhoMobileTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrabalhoMobileTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "homeScreen") {
                    composable("homeScreen") {
                        HomeScreen (
                            navController = navController
                        )
                    }

                    composable("jokeScreen") {
                        JokeScreen(
                            onNavigateToHistory = {
                                navController.navigate("historyScreen")
                            },

                            onBackClick = {
                                navController.popBackStack()
                            },

                            onTechniquesClick = {
                                navController.navigate("tecnicas_respiracao")
                            },
                            onShareClick = {
                                navController.navigate("homeScreen")
                            }
                        )
                    }

                    composable("historyScreen") {
                        JokeHistoryScreen(
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable("analisar_emocao") {
                        AnalisarEmocaoScreen(navController=navController)
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
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrabalhoMobileTheme {
        Greeting("Android")
    }
}