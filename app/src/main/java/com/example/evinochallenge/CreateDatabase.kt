package com.example.evinochallenge

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File


class CreateDatabase internal constructor(context: Context?) :
    SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO) {
    var db: SQLiteDatabase? = null
    override fun onCreate(db: SQLiteDatabase) {
        val sqlCategoria = ("CREATE TABLE " + TABELA_USUARIO + " ( "
                + ID + " integer primary key autoincrement,"
                + LOGIN + " text,"
                + SENHA + " text"
                + " );")
        val sqlSugestao = ("CREATE TABLE " + TABELA_FAVORITOS + " ( "
                + ID + " integer primary key autoincrement,"
                + ID_USUARIO + " integer,"
                + NOME_FAVORITO + " text,"
                + IMAGEM_FAVORITA + " text,"
                + PREVIEW_FAVORITO + " text,"
                + VISUALIZACOES_FAVORITO + " long,"
                + CANAIS_FAVORITO + " long"
                + " );")
        db.execSQL(sqlCategoria)
        db.execSQL(sqlSugestao)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABELA_USUARIO")
        db.execSQL("DROP TABLE IF EXISTS $TABELA_FAVORITOS")
        onCreate(db)
    }

    fun doesDatabaseExist(context: Context): Boolean {
        val dbFile: File = context.getDatabasePath(NOME_BANCO)
        return dbFile.exists()
    }

    companion object {
        const val NOME_BANCO = "banco.db"
        const val TABELA_USUARIO = "usuarios"
        const val TABELA_FAVORITOS = "favoritos"
        const val ID = "id"
        const val LOGIN = "login"
        const val SENHA = "senha"
        const val VERSAO = 3
        const val ID_USUARIO = "id_usuario"
        const val NOME_FAVORITO = "nome_favorito"
        const val IMAGEM_FAVORITA = "imagem_favorita"
        const val VISUALIZACOES_FAVORITO = "visualizacoes_favorito"
        const val CANAIS_FAVORITO = "canais_favorito"
        const val PREVIEW_FAVORITO = "preview_favorito"
    }
}