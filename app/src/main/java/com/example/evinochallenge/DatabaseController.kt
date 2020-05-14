package com.example.evinochallenge

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase


class DatabaseController(var baseContext: Context) {

    private lateinit var db: SQLiteDatabase
    private lateinit var banco: CreateDatabase
    private lateinit var contexto: Context
    fun iniciar() {
        banco = CreateDatabase(baseContext)
        contexto = baseContext
    }

    fun insereDadoFavorito(user: Int, name: String, image: String): String? {
        val valores: ContentValues
        val resultado: Long
        db = banco.writableDatabase
        valores = ContentValues()
        valores.put(CreateDatabase.ID_USUARIO, user)
        valores.put(CreateDatabase.NOME_FAVORITO, name)
        valores.put(CreateDatabase.IMAGEM_FAVORITA, image)
        resultado = db.insert(CreateDatabase.TABELA_FAVORITOS, null, valores)
        db.close()
        return if (resultado == -1L) "Erro ao inserir registro" else {
            "Registro Inserido com sucesso "
        }
    }

    fun carregaDadosFavoritos(user: Int): Cursor {
        val cursor: Cursor?
        val campos = arrayOf(
            CreateDatabase.ID,
            CreateDatabase.ID_USUARIO,
            CreateDatabase.NOME_FAVORITO,
            CreateDatabase.IMAGEM_FAVORITA
        )

        db = banco.readableDatabase
        if (!banco.doesDatabaseExist(contexto)) {
            banco.onCreate(db)
        }
        cursor =
            db.rawQuery("SELECT * FROM favoritos where id_usuario = " + user, null)
        if (cursor != null) {
            cursor.moveToFirst()
        }
        db.close()
        return cursor
    }

    fun carregaListaFavoritos(user: Int): ArrayList<String> {
        var lista: ArrayList<String> = ArrayList()
        var cursor: Cursor = carregaDadosFavoritos(user)
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(1))
            } while (cursor.moveToNext())
        }
        return lista
    }

    fun deletaRegistroFavorito(id: Int) {
        val where = CreateDatabase.ID + " = " + id
        db = banco.readableDatabase
        db.delete(CreateDatabase.TABELA_FAVORITOS, where, null)
        db.close()
    }

    fun insereUsuario(login: String?, senha: String?): String? {
        val valores: ContentValues
        val resultado: Long
        db = banco!!.writableDatabase
        valores = ContentValues()
        valores.put(CreateDatabase.LOGIN, login)
        valores.put(CreateDatabase.SENHA, senha)
        resultado = db.insert(CreateDatabase.TABELA_USUARIO, null, valores)
        db.close()
        return if (resultado == -1L) "Erro ao inserir registro" else {
            "Registro Inserido com sucesso "
        }
    }

    fun carregaUsuario(login: String, senha: String?): Cursor? {
        val cursor: Cursor?
        val campos = arrayOf(
            CreateDatabase.ID,
            CreateDatabase.LOGIN,
            CreateDatabase.SENHA
        )
        db = banco.readableDatabase
        if (!banco.doesDatabaseExist(contexto)) {
            banco.onCreate(db)
        }
        if (senha != null) {
            cursor =
                db.rawQuery(
                    "SELECT * FROM usuarios where login = " + login + " and senha = " + senha,
                    null
                )
        } else {
            cursor =
                db.rawQuery("SELECT * FROM usuarios where login = " + login, null)
        }
        if (cursor != null) {
            cursor.moveToFirst()
        }
        db.close()
        return cursor
    }

}