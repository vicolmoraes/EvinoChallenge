package com.example.evinochallenge.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.evinochallenge.DatabaseController
import com.example.evinochallenge.R
import com.example.evinochallenge.interactor.Interactor
import com.example.evinochallenge.router.Router
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable


class MainActivity : AppCompatActivity(),
    StandardActivity {
    override lateinit var interactor: Interactor
    private lateinit var etLogin: EditText
    private lateinit var etPass: EditText
    private lateinit var btEnter: Button
    private lateinit var crud: DatabaseController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Router.INSTANCE.setCleanArchitecture(this)

        setToolbar(getString(R.string.toolbarMain))
        findViews()
    }

    fun findViews() {
        etLogin = et_activity_main_login
        etPass = et_activity_main_pass
        btEnter = bt_activity_main_enter
        crud = DatabaseController(this)
        crud.iniciar()
        btEnter.setOnClickListener(View.OnClickListener {
            if (!etLogin.text.isEmpty() && !etPass.text.isEmpty()) {
                try {
                    crud.loadUser(etLogin.text.toString(), etPass.text.toString()) != null
                    val intent = Intent(this, TopGamesActivity::class.java)
                    intent.putExtra(
                        "USER",
                        crud.loadUser(etLogin.text.toString(), null) as Serializable
                    )
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(
                        baseContext,
                        R.string.warningLoginSenhaErrados,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {
                Toast.makeText(
                    baseContext,
                    R.string.warningLoginSenhaNaoPreenchidos,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    fun setToolbar(titulo: String?) {
        val toolbar: Toolbar = in_toolbar as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(titulo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        menu!!.findItem(R.id.item_fav).setVisible(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_add) {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        return true
    }

}




