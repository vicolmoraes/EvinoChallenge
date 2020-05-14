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

    fun insereListaCategoria(lista: ArrayList<String>) {
        for (categoria in lista)
            insereDadoCategoria(categoria)
    }

    fun insereDadoCategoria(titulo: String?): String? {
        val valores: ContentValues
        val resultado: Long
        db = banco.writableDatabase
        valores = ContentValues()
        valores.put(CreateDatabase.TITULO, titulo)
        resultado = db.insert(CreateDatabase.TABELA_CATEGORIA, null, valores)
        db.close()
        return if (resultado == -1L) "Erro ao inserir registro" else {
            "Registro Inserido com sucesso "
        }
    }

    fun carregaDadosCategoria(): Cursor {
        val cursor: Cursor?
        val campos = arrayOf(
            CreateDatabase.ID,
            CreateDatabase.TITULO
        )

        db = banco.readableDatabase
        if (!banco.doesDatabaseExist(contexto)) {
            banco.onCreate(db)
        }
        cursor =
            db.query(CreateDatabase.TABELA_CATEGORIA, campos, null, null, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
        }
        db.close()
        return cursor
    }

    fun carregaListaCategorias(): ArrayList<String> {
        var lista: ArrayList<String> = ArrayList()
        var cursor: Cursor = carregaDadosCategoria()
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(1))
            } while (cursor.moveToNext())
        }
        return lista
    }

    fun carregaListaSugestoes(): ArrayList<String> {
        var lista: ArrayList<String> = ArrayList()
        var cursor: Cursor = carregaDadosSugestao()
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(1))
            } while (cursor.moveToNext())
        }
        return lista
    }

    fun alteraRegistroCategoria(
        id: Int,
        titulo: String
    ) {
        val valores: ContentValues
        val where: String
        db = banco.writableDatabase
        where = CreateDatabase.ID + "=" + id
        valores = ContentValues()
        valores.put(CreateDatabase.TITULO, titulo)
        db.update(CreateDatabase.TABELA_CATEGORIA, valores, where, null)
        db.close()
    }

    fun deletaRegistroCategoria(id: Int) {
        val where = CreateDatabase.ID + " = " + id
        db = banco.readableDatabase
        db.delete(CreateDatabase.TABELA_CATEGORIA, where, null)
        db.close()
    }

    fun insereDadoSugestao(titulo: String?): String? {
        val valores: ContentValues
        val resultado: Long
        db = banco!!.writableDatabase
        valores = ContentValues()
        valores.put(CreateDatabase.TITULO, titulo)
        resultado = db.insert(CreateDatabase.TABELA_SUGESTAO, null, valores)
        db.close()
        return if (resultado == -1L) "Erro ao inserir registro" else {
            "Registro Inserido com sucesso "
        }
    }

    fun carregaDadosSugestao(): Cursor {
        val cursor: Cursor?
        val campos = arrayOf(
            CreateDatabase.ID,
            CreateDatabase.TITULO
        )
        db = banco.readableDatabase
        if (!banco.doesDatabaseExist(contexto)) {
            banco.onCreate(db)
        }
        cursor =
            db.query(CreateDatabase.TABELA_SUGESTAO, campos, null, null, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
        }
        db.close()
        return cursor
    }

    fun alteraRegistroSugestao(
        titulo: String
    ) {
        val valores: ContentValues
        val where: String
        db = banco.writableDatabase
        where = CreateDatabase.TITULO + "=" + titulo
        valores = ContentValues()
        valores.put(CreateDatabase.TITULO, titulo)
        db.update(CreateDatabase.TABELA_SUGESTAO, valores, where, null)
        db.close()
    }

    fun deletaRegistroSugestao(id: Int) {
        val where = CreateDatabase.ID + " = " + id
        db = banco.readableDatabase
        db.delete(CreateDatabase.TABELA_SUGESTAO, where, null)
        db.close()
    }
}