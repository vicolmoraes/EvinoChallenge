package com.example.evinochallenge.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.evinochallenge.DatabaseController
import com.example.evinochallenge.R
import com.example.evinochallenge.entity.Top
import com.example.evinochallenge.interactor.Interactor
import com.example.evinochallenge.router.Router
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity(),
    StandardActivity {
    lateinit var rvResults: RecyclerView
    lateinit var crud: DatabaseController
    override lateinit var interactor: Interactor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        Router.INSTANCE.setCleanArchitecture(this)
        findViews()
        setToolbar("Adicione jogos a sua lista de favoritos")
    }

    fun setToolbar(titulo: String?) {
        val toolbar: Toolbar = in_toolbar as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(titulo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.removeItem(R.id.item_add)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_fav) {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        return true
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