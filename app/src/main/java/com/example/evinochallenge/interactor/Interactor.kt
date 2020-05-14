package com.example.evinochallenge.interactor

import com.example.evinochallenge.entity.TopGames
import com.example.evinochallenge.presenter.Presenter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Interactor {
    lateinit var presenter: Presenter

    fun fetch() {
        val observable = RetrofitConfig().buildingService().list()
        observable
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TopGames> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    presenter.error(e.message)
                }

                override fun onNext(resposta: TopGames) {
                    presenter.results(resposta.result)
                }
            })
    }

}