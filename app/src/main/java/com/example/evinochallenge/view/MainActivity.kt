package com.example.evinochallenge.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.evinochallenge.R
import com.example.evinochallenge.entity.ChuckFact
import com.example.evinochallenge.interactor.Interactor
import com.example.evinochallenge.router.Router
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    ActivityPadrao {
    lateinit var rvResultados: RecyclerView
    var listaFatos: ArrayList<ChuckFact> = ArrayList()
    override lateinit var interactor: Interactor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Router.INSTANCE.setCleanArchitecture(this)

        setRecycler()
        iniciarToolbar("Bem Vindo")
    }

    fun iniciarToolbar(titulo: String?) {
        val toolbar: Toolbar = in_toolbar as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(titulo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title.equals("pesquisar")) {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            finish()
        }

        return true
    }

    private fun setRecycler() {
        rvResultados = rv_resultados_activity_main
        if (intent.extras?.get("FACTS") != null)
            listaFatos = intent.extras?.get("FACTS") as ArrayList<ChuckFact>
        rvResultados.adapter =
            FactAdapter(listaFatos, this, { partItem: ChuckFact -> partItemClicked(partItem) })
    }

    private fun partItemClicked(fato: ChuckFact) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, fato.url)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(sendIntent, null))
    }
}




