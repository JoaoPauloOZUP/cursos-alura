package com.zupacademy.scheduleofstudent.ui.helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.zupacademy.scheduleofstudent.database.dao.StudentDao
import com.zupacademy.scheduleofstudent.ui.adapter.StudentRecyclerAdapter
import com.zupacademy.scheduleofstudent.ui.viewmodel.StudentListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

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
        val initPosition = viewHolder.adapterPosition
        val endPosition = target.adapterPosition
        adapter.trade(initPosition, endPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        adapter.remove(position)
    }
}