package com.example.evinochallenge.interactor

import com.example.evinochallenge.Constants
import com.example.evinochallenge.entity.ChuckFact
import com.example.evinochallenge.entity.ChuckFacts
import com.example.evinochallenge.presenter.Presenter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Interactor {
    lateinit var presenter: Presenter
    fun fetchSearchRandom() {
        fetchRespostaUnica(Constants.RANDOM)
    }

    fun fetchSearchCategorie(categoria: String) {
        fetchRespostaUnica(Constants.SEARCH_CATEGORIE + categoria)
    }

    fun fetchSearchText(texto: String) {
        fetch(Constants.SEARCH_TEXT + texto)
    }

    private fun fetch(chave: String) {
        val observable = RetrofitConfig().buildingService().list(chave.trim())
        observable
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ChuckFacts> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    presenter.exibirErro(e.message)
                }

                override fun onNext(resposta: ChuckFacts) {
                    presenter.exibirResultados(resposta.result)
                }
            })
    }

    fun fetchSearchListCategories() {
        val observable = RetrofitConfig().buildingService().listCategories()
        observable
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<String>> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    presenter.exibirErro(e.message)
                }

                override fun onNext(resposta: List<String>) {
                    presenter.exibirCategorias(resposta)
                }
            })
    }

    private fun fetchRespostaUnica(chave: String) {

        val observable = RetrofitConfig().buildingService().respostaUnica(chave)
        observable
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ChuckFact> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    presenter.exibirErro(e.message + e.cause)
                }

                override fun onNext(resposta: ChuckFact) {
                    presenter.exibirResultado(resposta)
                }
            })
    }
}