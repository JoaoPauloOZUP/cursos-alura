package com.example.agenda.ui.activity.adapter.helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.dao.StudentDAO
import com.example.agenda.ui.activity.adapter.StudentRecyclerAdapter

class StudentItemTouchCallback(
    private val adapter: StudentRecyclerAdapter
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
        StudentDAO().remove(slippedStudentPosition)
        adapter.remove(slippedStudentPosition)
    }
}
