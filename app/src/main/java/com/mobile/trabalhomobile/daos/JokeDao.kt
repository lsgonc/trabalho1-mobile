package com.mobile.trabalhomobile.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.trabalhomobile.models.joke.Joke

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: Joke)

    @Query("SELECT * FROM jokes ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomJoke(): Joke?

    @Query("SELECT * FROM jokes ORDER BY id DESC")
    suspend fun getAllJokes(): List<Joke>
}