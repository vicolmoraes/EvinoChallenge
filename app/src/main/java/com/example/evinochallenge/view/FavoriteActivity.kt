package com.example.evinochallenge.view

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.evinochallenge.DatabaseController
import com.example.evinochallenge.R
import com.example.evinochallenge.entity.Top
import com.example.evinochallenge.entity.User
import com.example.evinochallenge.interactor.Interactor
import com.example.evinochallenge.router.Router
import kotlinx.android.synthetic.main.activity_search.*


class FavoriteActivity : AppCompatActivity(),
    StandardActivity {
    lateinit var rvResults: RecyclerView
    lateinit var crud: DatabaseController
    lateinit var user: User
    override lateinit var interactor: Interactor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        Router.INSTANCE.setCleanArchitecture(this)
        findViews()
        setToolbar(user.login + " favoritos")
    }

    fun setToolbar(titulo: String?) {
        val toolbar: Toolbar = in_toolbar as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(titulo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.removeItem(R.id.item_add)
        menu?.removeItem(R.id.item_fav)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun findViews() {
        crud = DatabaseController(this)
        crud.iniciar()
        rvResults = rv_results_activity_search
        if (intent.extras?.get("USER") != null)
            user = intent.extras?.get("USER") as User
        setRecycler(crud.carregaListaFavoritos(user.id))

    }

    private fun setRecycler(gamesList: ArrayList<Top?>) {
        rvResults.adapter =
            GameAdapter(gamesList, this, { partItem: Top? -> partItemClicked(partItem) })
    }

    private fun partItemClicked(game: Top?) {
        Toast.makeText(
            baseContext,
            "Desfavoritado",
            Toast.LENGTH_SHORT
        ).show()
        crud.deletaRegistroFavorito(game?.game?.id)
        setRecycler(crud.carregaListaFavoritos(user.id))
    }
}