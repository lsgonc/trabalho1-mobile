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
fun TecnicasRespiracaoScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    var openDialog by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Técnicas de Respiração") },
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
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { openDialog = "478" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Técnica 4-7-8", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { openDialog = "alternadas" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Respiração das Narinas Alternadas", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { openDialog = "relaxamento" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Relaxamento Progressivo", fontSize = 16.sp)
            }
        }
    }

    if (openDialog != null) {
        val (title, description, url) = when (openDialog) {
            "478" -> Triple(
                "Técnica 4-7-8",
                "Inspire por 4 segundos, segure o ar por 7 segundos e expire por 8 segundos. Excelente para relaxamento e controle da ansiedade.",
                "https://www.cnnbrasil.com.br/saude/respiracao-4-7-8-como-usar-a-tecnica-para-dormir-ou-reduzir-a-ansiedade/"
            )
            "alternadas" -> Triple(
                "Respiração das Narinas Alternadas",
                "Também conhecida como Nadi Shodhana, essa técnica equilibra os dois hemisférios do cérebro e ajuda a acalmar a mente.",
                "https://www.artofliving.org/br-pt/yoga/yoga-e-respiracao/tecnica-de-respiracao-das-narinas-alternadas-nadi-shodhan-pranayama"
            )
            "relaxamento" -> Triple(
                "Relaxamento Progressivo",
                "Consiste em contrair e relaxar cada grupo muscular do corpo para promover alívio da tensão física e mental.",
                "https://www.psymeetsocial.com/blog/artigos/relaxamento-muscular-progressivo"
            )
            else -> Triple("", "", "")
        }

        AlertDialog(
            onDismissRequest = { openDialog = null },
            confirmButton = {
                TextButton(
                    onClick = {
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