package com.mobile.trabalhomobile.repositories

import com.mobile.trabalhomobile.daos.JokeDao
import com.mobile.trabalhomobile.models.joke.Joke
import com.mobile.trabalhomobile.services.JokeApiService

class JokeRepository(
    private val api: JokeApiService,
    private val jokeDao: JokeDao
) {
    suspend fun getRandomJoke(): Result<Joke> = try {
        val joke = api.getRandomJoke()
        val jokeWithDate = joke.copy(displayedAt = System.currentTimeMillis())
        jokeDao.insertJoke(jokeWithDate) // Salva a piada no banco
        Result.success(jokeWithDate)
    } catch (e: Exception) {
        // Tenta buscar uma piada do banco em caso de falha
        val localJoke = jokeDao.getRandomJoke()
        if (localJoke != null) {
            Result.success(localJoke)
        } else {
            Result.failure(e)
        }
    }

    suspend fun getJokeHistory(): List<Joke> {
        return jokeDao.getAllJokes()
    }
}