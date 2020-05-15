package com.example.evinochallenge.view

import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_top_games.*


class FavoriteActivity : AppCompatActivity(),
    StandardActivity {
    private lateinit var rvResults: RecyclerView
    private lateinit var crud: DatabaseController
    private lateinit var user: User
    override lateinit var interactor: Interactor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_games)

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
        bt_top_games_logout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent)
        }

        crud = DatabaseController(this)
        crud.iniciar()

        rvResults = rv_top_games_lista
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