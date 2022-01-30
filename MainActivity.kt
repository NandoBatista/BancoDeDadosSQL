package com.batista.bancodedados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            //Criar Banco de Dados
            val bancoDados = openOrCreateDatabase("DB_FORNECEDORES", MODE_PRIVATE, null)

            //Criar uma tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS fornecedores(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR,telefone INT(4))")

            //Inserir dados em uma tabela
            //bancoDados.execSQL("INSERT INTO fornecedores(nome,telefone) VALUES ('Cia Ultragaz S.A',10)")
            //bancoDados.execSQL("INSERT INTO fornecedores(nome,telefone) VALUES ('Academia Gourmet',20)")
            //bancoDados.execSQL("INSERT INTO fornecedores(nome,telefone) VALUES ('Mogo Sistemas',30)")
            //bancoDados.execSQL("INSERT INTO fornecedores(nome,telefone) VALUES ('Tramontina',40)")

            //Atualizar registros
            //bancoDados.execSQL("UPDATE fornecedores SET telefone = 50, nome = 'for1' WHERE nome = 'Cia Ultragaz S.A'")

            //Remover uma tabela
            //bancoDados.execSQL("DROP TABLE fornecedores")

            //Remover registros
            bancoDados.execSQL("DELETE FROM fornecedores WHERE id = 1")

            val consulta = "SELECT id,nome,telefone FROM fornecedores "


            val cursor = bancoDados.rawQuery(consulta, null)

            //Recuperar os índices da nossa tabela
            val indiceId = cursor.getColumnIndex("id")
            val indiceNome = cursor.getColumnIndex("nome")
            val indiceTelefone = cursor.getColumnIndex("telefone")
            cursor.moveToFirst()

            while (cursor != null) {

                val id = cursor.getString(indiceId)
                val nome = cursor.getString(indiceNome)
                val telefone = cursor.getString(indiceTelefone)
                Log.i("RESULTADO - id", "$id /nome: $nome/ telefone: $telefone")
                cursor.moveToNext()

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}