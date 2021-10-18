package com.example.agenda

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listStudents = listOf("Pedro", "Thiago", "Maria", "João")

        /**
         * O this é o contexto da aplicação e neste caso é o lugar onde será exibida listViewStudents.
         * Já o layout simples_list_item_1 é a view que será inclusa na listViewStudents. Cada students será posta nesta view.
         * A listStudents é a lista que alimentará a listViewStudents
         * */
        findViewById<ListView>(R.id.activity_main_list_student).let { listViewStudents ->
            listViewStudents.adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                listStudents
            )
        }
    }
}