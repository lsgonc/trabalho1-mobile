package com.mobile.trabalhomobile.compose

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DicasEstudoScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    // Estado para controlar qual método está aberto
    var openDialog by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dicas de Estudo") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E1E1E)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = { openDialog = "pomodoro" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Método Pomodoro", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { openDialog = "mnemonica" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Estudo Mnemônico", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { openDialog = "intercalacao" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Intercalação de Matérias", fontSize = 16.sp)
            }
        }
    }

    // Dialog para mostrar o conteúdo
    if (openDialog != null) {
        val (title, description, url) = when (openDialog) {
            "pomodoro" -> Triple(
                "Método Pomodoro",
                "O conceito do Pomodoro é dividir o tempo de estudo em blocos de 25 minutos, seguidos por pausas de 5 minutos para descansar e melhorar a concentração.",
                "https://brasilescola.uol.com.br/dicas-de-estudo/tecnica-pomodoro-que-e-e-como-funciona.htm"
            )
            "mnemonica" -> Triple(
                "Estudo Mnemônico",
                "Estudo mnemônico é uma técnica que usa associações e recursos visuais para ajudar na memorização de conteúdos complexos.",
                "https://www.educamaisbrasil.com.br/educacao/noticias/o-que-e-estudo-mnemonico-conheca-a-tecnica"
            )
            "intercalacao" -> Triple(
                "Intercalação de Matérias",
                "Essa técnica consiste em intercalar diferentes matérias durante o estudo para aumentar a retenção e evitar o cansaço mental.",
                "https://www.educamaisbrasil.com.br/educacao/dicas/o-que-e-estudo-intercalado"
            )
            else -> Triple("", "", "")
        }

        AlertDialog(
            onDismissRequest = { openDialog = null },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Abre o navegador com o link
                        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                        context.startActivity(intent)
                    }
                ) {
                    Text("Abrir site")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog = null }) {
                    Text("Fechar")
                }
            },
            title = { Text(title) },
            text = { Text(description) }
        )
    }
}