package com.example.evinochallenge.view

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.evinochallenge.DatabaseController
import com.example.evinochallenge.R
import com.example.evinochallenge.entity.Top
import com.example.evinochallenge.interactor.Interactor
import com.example.evinochallenge.router.Router
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity(),
    StandardActivity {
    lateinit var etBusca: EditText
    lateinit var rvResults: RecyclerView
    lateinit var crud: DatabaseController
    override lateinit var interactor: Interactor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        Router.INSTANCE.setCleanArchitecture(this)
        findViews()
    }

    private fun findViews() {
        interactor.fetch()
    }

    override fun error(resposta: String?) {
        Toast.makeText(
            baseContext,
            resposta,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun results(resposta: List<Top>) {
        val lista: ArrayList<Top> = ArrayList()
        if (resposta.size > 100)
            lista.addAll(resposta.subList(0, 100))
        else lista.addAll(resposta)
        setRecycler(lista)
    }

    private fun setRecycler(gamesList: ArrayList<Top>) {
        rvResults = rv_results_activity_search
        rvResults.adapter =
            FactAdapter(gamesList, this, { partItem: Top -> partItemClicked(partItem) })
    }

    private fun partItemClicked(fato: Top) {

    }
}