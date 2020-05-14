package com.example.evinochallenge.view

import android.os.Bundle
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
        setToolbar(user.login + getString(R.string.toolbarFavorite))
    }

    fun setToolbar(titulo: String?) {
        val toolbar: Toolbar = in_toolbar as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(titulo)
    }

    private fun findViews() {
        crud = DatabaseController(this)
        crud.iniciar()
        rvResults = rv_results_activity_search
        if (intent.extras?.get("USER") != null)
            user = intent.extras?.get("USER") as User
        setRecycler(crud.loadFavoritesList(user.id))
    }

    private fun setRecycler(gamesList: ArrayList<Top?>) {
        rvResults.adapter =
            GameAdapter(gamesList, this, { partItem: Top? -> partItemClicked(partItem) })
    }

    private fun partItemClicked(game: Top?) {
        Toast.makeText(
            baseContext,
            R.string.desfavoritado,
            Toast.LENGTH_SHORT
        ).show()
        crud.deleteFavorite(game?.game?.id)
        setRecycler(crud.loadFavoritesList(user.id))
    }
}