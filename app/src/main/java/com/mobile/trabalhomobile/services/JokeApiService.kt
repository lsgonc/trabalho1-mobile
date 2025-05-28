package com.mobile.trabalhomobile.services

import com.mobile.trabalhomobile.models.joke.Joke
import retrofit2.Retrofit
import retrofit2.http.GET

interface JokeApiService {
    @GET("random_joke")
    suspend fun getRandomJoke(): Joke
}
