package com.example.evinochallenge

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File


class CreateDatabase internal constructor(context: Context?) :
    SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO) {
    var db: SQLiteDatabase? = null
    override fun onCreate(db: SQLiteDatabase) {
        val sqlCategoria = ("CREATE TABLE " + TABELA_CATEGORIA + " ( "
                + ID + " integer primary key autoincrement,"
                + TITULO + " text"
                + " );")
        val sqlSugestao = ("CREATE TABLE " + TABELA_SUGESTAO + " ( "
                + ID + " integer primary key autoincrement,"
                + TITULO + " text"
                + " );")
        db.execSQL(sqlCategoria)
        db.execSQL(sqlSugestao)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABELA_CATEGORIA")
        db.execSQL("DROP TABLE IF EXISTS $TABELA_SUGESTAO")
        onCreate(db)
    }

    fun doesDatabaseExist(context: Context): Boolean {
        val dbFile: File = context.getDatabasePath(NOME_BANCO)
        return dbFile.exists()
    }

    companion object {
        const val NOME_BANCO = "banco.db"
        const val TABELA_CATEGORIA = "categorias"
        const val TABELA_SUGESTAO = "sugestoes"
        const val ID = "id"
        const val TITULO = "titulo"
        const val VERSAO = 2
    }
}