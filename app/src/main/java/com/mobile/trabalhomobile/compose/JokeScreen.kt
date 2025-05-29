package com.mobile.trabalhomobile.compose

import android.app.Application
import android.webkit.WebSettings
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mobile.trabalhomobile.viewmodels.JokeState
import com.mobile.trabalhomobile.viewmodels.JokeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.mobile.trabalhomobile.viewmodels.AnalisarEmocaoViewModel
import com.mobile.trabalhomobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeScreen(
    onNavigateToHistory: () -> Unit,
    viewModel: JokeViewModel = viewModel(),
    emocaoViewModel: AnalisarEmocaoViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onTechniquesClick: () -> Unit = {}
) {
    var showAnswer by remember { mutableStateOf(false) }
    val context = LocalContext.current
    emocaoViewModel.sortearEmocao(context.resources.getStringArray(R.array.lista_emocoes).toList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.piadas_titulo)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E1E1E)
                ),
                actions = {
                    TextButton(onClick = onNavigateToHistory) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                stringResource(id = R.string.historico),
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Espaço entre o texto e o ícone
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Histórico",
                                tint = Color.White
                            )
                        }
                    }
                }
            )

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when (val state = viewModel.jokeState.value) {
                    is JokeState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    is JokeState.Success -> {
                        Text(
                            text = emocaoViewModel.emocao,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.piadas_texto2),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = state.joke.setup,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                        AnswerBox(
                            answer = state.joke.punchline,
                            showAnswer = showAnswer,
                            onToggleAnswer = { showAnswer = !showAnswer }
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                onClick = {
                                    showAnswer = false
                                    viewModel.loadNewJoke()
                                },
                                modifier = Modifier.wrapContentHeight(),
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4579FE)
                                )
                            ) {
                                Text(stringResource(id = R.string.conte_outra))
                            }
                        }
                    }
                    is JokeState.Error -> {
                        Text(
                            text = state.message,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                        Button(
                            onClick = { viewModel.loadNewJoke() },
                            modifier = Modifier
                                .padding(16.dp)
                                .wrapContentHeight(),
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4579FE)
                            )
                        ) {
                            Text(stringResource(id = R.string.tentar_novamente))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ActionButtons(
                onTechniquesClick = onTechniquesClick,
                onShareClick = onShareClick,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}

@Composable
private fun AnswerBox(
    answer: String,
    showAnswer: Boolean,
    onToggleAnswer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .border(1.dp, Color.Gray, MaterialTheme.shapes.medium)
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = if (showAnswer) answer else "********",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onToggleAnswer) {
                Icon(
                    imageVector = if (showAnswer) Icons.Default.Create else Icons.Default.Create,
                    contentDescription = if (showAnswer) "Esconder resposta" else "Revelar resposta"
                )
            }
        }
    }
}

@Composable
private fun ActionButtons(
    onTechniquesClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = onTechniquesClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF31B768)
            )
        ) {
            Text(stringResource(id = R.string.tecnicas_sentir_melhor))
        }

        Button(
            onClick = onShareClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD02F27)
            )
        ) {
            Text(stringResource(id = R.string.agora_nao))
        }
    }
}