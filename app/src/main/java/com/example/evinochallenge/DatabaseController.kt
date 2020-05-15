package com.example.evinochallenge

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.evinochallenge.entity.Game
import com.example.evinochallenge.entity.Image
import com.example.evinochallenge.entity.Top
import com.example.evinochallenge.entity.User


class DatabaseController(var baseContext: Context) {

    private lateinit var db: SQLiteDatabase
    private lateinit var banco: CreateDatabase
    private lateinit var contexto: Context
    fun iniciar() {
        banco = CreateDatabase(baseContext)
        contexto = baseContext
    }

    fun insertFavorites(user: Int, top: Top?): String? {
        val valores: ContentValues
        var resultado: Long
        db = banco.writableDatabase
        valores = ContentValues()
        valores.put(CreateDatabase.ID_USUARIO, user.toLong())
        valores.put(CreateDatabase.NOME_FAVORITO, top?.game?.localized_name)
        valores.put(CreateDatabase.IMAGEM_FAVORITA, top?.game?.box?.small)
        valores.put(CreateDatabase.PREVIEW_FAVORITO, top?.game?.logo?.small)
        valores.put(CreateDatabase.VISUALIZACOES_FAVORITO, top?.viewers)
        valores.put(CreateDatabase.CANAIS_FAVORITO, top?.channels)
        try {
            resultado = db.update(
                CreateDatabase.TABELA_FAVORITOS,
                valores,
                CreateDatabase.ID_USUARIO + " = ?" + user,
                arrayOf(user.toString())
            ).toLong()
        } catch (e: Exception) {
            resultado = db.insert(CreateDatabase.TABELA_FAVORITOS, null, valores)
        }
        db.close()
        return if (resultado == -1L) "Erro ao inserir registro" else {
            "Registro Inserido com sucesso "
        }
    }

    fun loadFavorites(user: Int): Cursor {
        val cursor: Cursor?
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

    fun loadFavoritesList(user: Int): ArrayList<Top?> {
        var lista: ArrayList<Top?> = ArrayList()
        var cursor: Cursor = loadFavorites(user)
        if (cursor.moveToFirst()) {
            do {
                val box = Image(
                    "",
                    "",
                    small = cursor.getString(cursor.getColumnIndex(CreateDatabase.IMAGEM_FAVORITA)),
                    template = ""
                )
                val logo = Image(
                    "",
                    "",
                    small = cursor.getString(cursor.getColumnIndex(CreateDatabase.PREVIEW_FAVORITO)),
                    template = ""
                )
                val game = Game(
                    id = cursor.getLong(cursor.getColumnIndex(CreateDatabase.ID)),
                    name = "",
                    giantbomb_id = null,
                    locale = "",
                    logo = logo,
                    localized_name = cursor.getString(cursor.getColumnIndex(CreateDatabase.NOME_FAVORITO)),
                    box = box
                )
                lista.add(
                    Top(
                        channels = cursor.getLong(cursor.getColumnIndex(CreateDatabase.CANAIS_FAVORITO)),
                        viewers = cursor.getLong(cursor.getColumnIndex(CreateDatabase.VISUALIZACOES_FAVORITO)),
                        game = game
                    )
                )

            } while (cursor.moveToNext())
        }
        return lista
    }

    fun deleteFavorite(id: Long?) {
        val where = CreateDatabase.ID + " = " + id
        db = banco.readableDatabase
        db.delete(CreateDatabase.TABELA_FAVORITOS, where, null)
        db.close()
    }

    fun insertUser(login: String?, senha: String?): String? {
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

    fun loadUser(login: String, senha: String?): User? {
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
                    "SELECT * FROM usuarios where login = ? and senha = ?",
                    arrayOf(login, senha)
                )
        } else {
            cursor =
                db.rawQuery("SELECT * FROM usuarios where login = ?", arrayOf(login))
        }
        if (cursor != null) {
            cursor.moveToFirst()
        }
        db.close()

        var user = User(
            cursor.getInt(cursor.getColumnIndex(CreateDatabase.ID)),
            cursor.getString(cursor.getColumnIndex(CreateDatabase.LOGIN)),
            cursor.getString(cursor.getColumnIndex(CreateDatabase.SENHA))
        )

        return user
    }

}