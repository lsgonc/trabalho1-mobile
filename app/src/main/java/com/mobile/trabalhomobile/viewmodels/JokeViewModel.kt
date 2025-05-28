package com.mobile.trabalhomobile.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.mobile.trabalhomobile.database.AppDatabase
import com.mobile.trabalhomobile.models.joke.Joke
import com.mobile.trabalhomobile.repositories.JokeRepository
import com.mobile.trabalhomobile.services.JokeApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class JokeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JokeRepository

    val jokeState = mutableStateOf<JokeState>(JokeState.Loading)

    init {
        val database = Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app-database"
        ).build()

        val apiService = createApiService()
        repository = JokeRepository(apiService, database.jokeDao())

        loadNewJoke()
    }

    private fun createApiService(): JokeApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("https://official-joke-api.appspot.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(JokeApiService::class.java)
    }

    fun loadNewJoke() {
        viewModelScope.launch {
            jokeState.value = JokeState.Loading
            repository.getRandomJoke()
                .onSuccess { jokeState.value = JokeState.Success(it) }
                .onFailure { jokeState.value = JokeState.Error(it.message ?: "Erro ao carregar piada") }
        }
    }

    val jokeHistory = mutableStateOf<List<Joke>>(emptyList())

    fun loadJokeHistory() {
        viewModelScope.launch {
            jokeHistory.value = repository.getJokeHistory()
        }
    }

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(JokeViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return JokeViewModel(application = Application()) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}

sealed class JokeState {
    object Loading : JokeState()
    data class Success(val joke: Joke) : JokeState()
    data class Error(val message: String) : JokeState()
}