package com.example.agenda.ui.activity.adapter.helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.agenda.dao.StudentDAO
import com.example.agenda.database.Database
import com.example.agenda.database.room_dao.RoomStudentDAO
import com.example.agenda.ui.activity.adapter.StudentRecyclerAdapter

class StudentItemTouchCallback(
    private val adapter: StudentRecyclerAdapter,
    private val dao: RoomStudentDAO
) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val slideMarks = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        val dragMarks = ItemTouchHelper.DOWN or ItemTouchHelper.UP or ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        return makeMovementFlags(dragMarks, slideMarks)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val positionInit = viewHolder.adapterPosition
        val positionEnd = target.adapterPosition
        StudentDAO().trade(positionInit, positionEnd)
        adapter.trade(positionInit, positionEnd)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val slippedStudentPosition = viewHolder.adapterPosition
        val student = adapter.getElement(slippedStudentPosition)
        dao.remove(student)
        adapter.remove(slippedStudentPosition)
    }
}
