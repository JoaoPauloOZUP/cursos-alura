package com.zupacademy.scheduleofstudent.ui.listerner

abstract class OnItemClickListener<E> {
    open fun onClick(item: E) {}
    open fun onClick(item: E, position: Int) {}
}