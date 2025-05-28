package com.mobile.trabalhomobile.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobile.trabalhomobile.daos.JokeDao
import com.mobile.trabalhomobile.models.joke.Joke

@Database(entities = [Joke::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}