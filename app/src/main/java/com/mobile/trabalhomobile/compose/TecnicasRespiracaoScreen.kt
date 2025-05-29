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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.mobile.trabalhomobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TecnicasRespiracaoScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    var openDialog by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.tecnicas_respiracao_titulo)) },
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
                Text(
                    stringResource(id = R.string.tecnica1),
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { openDialog = "alternadas" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    stringResource(id = R.string.tecnica2),
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { openDialog = "relaxamento" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    stringResource(id = R.string.tecnica3),
                    fontSize = 16.sp
                )
            }
        }
    }

    if (openDialog != null) {
        val (title, description, url) = when (openDialog) {
            "478" -> Triple(
                stringResource(id = R.string.tecnica1),
                stringResource(id = R.string.tecnica1_explicacao),
                "https://www.cnnbrasil.com.br/saude/respiracao-4-7-8-como-usar-a-tecnica-para-dormir-ou-reduzir-a-ansiedade/"
            )
            "alternadas" -> Triple(
                stringResource(id = R.string.tecnica2),
                stringResource(id = R.string.tecnica2_explicacao),
                "https://www.artofliving.org/br-pt/yoga/yoga-e-respiracao/tecnica-de-respiracao-das-narinas-alternadas-nadi-shodhan-pranayama"
            )
            "relaxamento" -> Triple(
                stringResource(id = R.string.tecnica3),
                stringResource(id = R.string.tecnica3_explicacao),
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
                    Text(stringResource(id = R.string.abrir_site))
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog = null }) {
                    Text(stringResource(id = R.string.fechar))
                }
            },
            title = { Text(title) },
            text = { Text(description) }
        )
    }
}