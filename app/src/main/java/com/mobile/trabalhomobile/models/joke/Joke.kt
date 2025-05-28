package com.mobile.trabalhomobile.models.joke;

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes")
data class Joke(
    val type: String,
    val setup: String,
    val punchline: String,
    @PrimaryKey val id: String,
    val displayedAt: Long = System.currentTimeMillis() // campo timestamp
)