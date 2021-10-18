package com.example.agenda

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Students"
        setContentView(R.layout.activity_main)

        println("ON_CREATE")

        /**
         * O this é o contexto da aplicação e neste caso é o lugar onde será exibida listViewStudents.
         * Já o layout simples_list_item_1 é a view que será inclusa na listViewStudents. Cada students será posta nesta view.
         * A listStudents é a lista que alimentará a listViewStudents
         * */
        val listStudents = listOf("Pedro", "Thiago", "Maria", "João")
        findViewById<ListView>(R.id.activity_main_list_student).let { listViewStudents ->
            listViewStudents.adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                listStudents
            )
        }

        findViewById<FloatingActionButton>(R.id.activity_main_fab_new_student).let { fabNewStudent ->
            fabNewStudent.setOnClickListener {
                println("Cliquei no float button")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("ON_START")
    }

    override fun onResume() {
        super.onResume()
        println("ON_RESUME")
    }

    override fun onPause() {
        super.onPause()
        println("ON_PAUSE")
    }

    override fun onStop() {
        super.onStop()
        println("ON_STOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("ON_DESTROY")
    }
}