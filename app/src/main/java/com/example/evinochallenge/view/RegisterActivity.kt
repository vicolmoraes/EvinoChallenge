package com.example.evinochallenge.view

import android.content.Intent
import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_register.*
import java.io.Serializable

class RegisterActivity : AppCompatActivity(),
    StandardActivity {
    private lateinit var etLogin: EditText
    private lateinit var etPass: EditText
    private lateinit var btRegister: Button
    private lateinit var crud: DatabaseController
    override lateinit var interactor: Interactor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Router.INSTANCE.setCleanArchitecture(this)

        findViews()
        setToolbar(getString(R.string.toolbarRegister))
    }

    fun setToolbar(titulo: String?) {
        val toolbar: Toolbar = in_toolbar as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(titulo)
    }

    fun findViews() {
        etLogin = et_activity_register_login
        etPass = et_activity_register_pass
        btRegister = bt_activity_register_register
        crud = DatabaseController(this)
        crud.iniciar()
        btRegister.setOnClickListener(View.OnClickListener {
            if (!etLogin.text.isEmpty() && !etPass.text.isEmpty()) {
                try {
                    crud.loadUser(etLogin.text.toString(), null) != null
                    Toast.makeText(
                        baseContext,
                        R.string.warningLoginJaExistente,
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    crud.insertUser(etLogin.text.toString(), etPass.text.toString())
                    val intent = Intent(this, TopGamesActivity::class.java)
                    intent.putExtra(
                        "USER",
                        crud.loadUser(etLogin.text.toString(), null) as Serializable
                    )
                    startActivity(intent)
                    finish()
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
}




