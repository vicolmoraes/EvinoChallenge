package com.example.evinochallenge.view

import com.example.evinochallenge.entity.ChuckFact
import com.example.evinochallenge.interactor.Interactor

interface ActivityPadrao {
    var interactor: Interactor
    fun exibirErro(resposta: String?) {
    }

    fun exibirResultado(resposta: ChuckFact) {
    }

    fun listarRespostas(resposta: List<ChuckFact>) {
    }

    fun listarCategorias(resposta: List<String>) {
    }
}