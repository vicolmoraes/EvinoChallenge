package com.example.evinochallenge.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evinochallenge.DatabaseController
import com.example.evinochallenge.R
import com.example.evinochallenge.interactor.Interactor
import com.example.evinochallenge.router.Router
import kotlinx.android.synthetic.main.activity_register.*
import java.io.Serializable

class RegisterActivity : AppCompatActivity(),
    StandardActivity {
    lateinit var etLogin: EditText
    lateinit var etPass: EditText
    lateinit var btRegister: Button
    lateinit var crud: DatabaseController
    override lateinit var interactor: Interactor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Router.INSTANCE.setCleanArchitecture(this)

        findViews()
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
                    crud.carregaUsuario(etLogin.text.toString(), null) != null
                    Toast.makeText(
                        baseContext,
                        "Este login já existe",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    crud.insereUsuario(etLogin.text.toString(), etPass.text.toString())
                    val intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra(
                        "USER",
                        crud.carregaUsuario(etLogin.text.toString(), null) as Serializable
                    )
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(
                    baseContext,
                    "O campo login e senha precisam ser preenchidos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}




