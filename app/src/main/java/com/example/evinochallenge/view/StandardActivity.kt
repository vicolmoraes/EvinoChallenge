package com.example.evinochallenge.view

import com.example.evinochallenge.entity.Top
import com.example.evinochallenge.interactor.Interactor

interface StandardActivity {
    var interactor: Interactor
    fun error(resposta: String?) {
    }

    fun results(resposta: List<Top?>) {
    }

}