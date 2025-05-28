package com.mobile.trabalhomobile.viewmodels

import androidx.lifecycle.ViewModel

class AnalisarEmocaoViewModel : ViewModel() {
    var emocao: String = ""
        private set

    fun sortearEmocao() {
        val lista = listOf("Feliz", "Triste", "Bravo", "Entediado")
        emocao = lista.random()
    }
}