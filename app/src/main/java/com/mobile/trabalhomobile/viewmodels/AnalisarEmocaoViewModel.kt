package com.mobile.trabalhomobile.viewmodels

import androidx.lifecycle.ViewModel

class AnalisarEmocaoViewModel : ViewModel() {
    var emocao: String = ""
        private set

    fun sortearEmocao(lista: List<String>) {
        emocao = lista.random()
    }
}