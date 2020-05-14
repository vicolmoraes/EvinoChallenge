package com.example.evinochallenge.presenter

import com.example.evinochallenge.entity.Top
import com.example.evinochallenge.view.StandardActivity

class Presenter {

    lateinit var activity: StandardActivity

    fun error(resposta: String?) {
        activity.error(resposta)
    }

    fun results(resposta: List<Top?>) {
        activity.results(resposta)
    }

}