package com.example.evinochallenge.router

import com.example.evinochallenge.interactor.Interactor
import com.example.evinochallenge.presenter.Presenter
import com.example.evinochallenge.view.ActivityPadrao

enum class Router {

    INSTANCE;

    fun setCleanArchitecture(activity: ActivityPadrao) {
        val presenter = Presenter()
        presenter.activity = activity

        val interactor = Interactor()
        interactor.presenter = presenter

        activity.interactor = interactor
    }
}