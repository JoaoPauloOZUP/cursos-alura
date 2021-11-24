package com.zupacademy.scheduleofstudent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.database.repository.StudentRepository
import com.zupacademy.scheduleofstudent.ui.listerner.OnItemClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*

class StudentRecyclerAdapter(
    private val context: Context,
    private val studentList: MutableList<Student>,
    private val repository: StudentRepository
) : RecyclerView.Adapter<StudentRecyclerAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener<Student>

    inner class ViewHolder(
        view: View,
        private val onItemClickListener: OnItemClickListener<Student>,
    ) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                val student = studentList[adapterPosition]
                val position = adapterPosition
                onItemClickListener.onClick(student, position)
            }
        }

        val name: TextView = view.findViewById(R.id.list_item_name)
        val phone: TextView = view.findViewById(R.id.list_item_phone)

        fun bind(student: Student) {
            name.text = student.name
            phone.text = student.phone

            student.indice = adapterPosition
            CoroutineScope(IO).launch {
                repository.updateIndice(student.id, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.list_item, parent, false)

        return this.ViewHolder(createdView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        studentList[position].let { student ->
            holder.bind(student)
        }
    }

    override fun getItemCount(): Int {
        return studentList.count()
    }

    fun save(student: Student) {
        studentList.add(student)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Student {
        return studentList[position]
    }

    fun edit(student: Student) {
        val position = student.indice!!
        studentList[position] = student
        notifyItemChanged(position)
    }

    fun remove(position: Int) {
        val student = studentList[position]
        repository.deleteStudent(student) {
            studentList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun trade(initPosition: Int, endPosition: Int) {
        val studentAtInitPosition = studentList[initPosition]
        val studentAtEndPosition = studentList[endPosition]

        studentAtInitPosition.indice = endPosition
        studentAtEndPosition.indice = initPosition

        CoroutineScope(IO).launch {
            repository.updateIndice(studentAtEndPosition.id, initPosition)
            repository.updateIndice(studentAtInitPosition.id, endPosition)
        }

        Collections.swap(studentList, initPosition, endPosition)
        notifyItemMoved(initPosition, endPosition)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<Student>) {
        this.onItemClickListener = onItemClickListener
    }
}