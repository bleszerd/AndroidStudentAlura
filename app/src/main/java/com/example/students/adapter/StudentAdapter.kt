package com.example.students.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.students.R
import com.example.students.model.Student

class StudentAdapter(private val context: Context) : BaseAdapter() {
    val students = mutableListOf<Student>()

    override fun getCount(): Int {
        return students.size
    }

    override fun getItem(position: Int): Student {
        return students[position]
    }

    override fun getItemId(position: Int): Long {
        return students[position].getId().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            LayoutInflater.from(context).inflate(R.layout.student_list_item, parent, false)

        view.findViewById<TextView>(R.id.textview_student_name).text = getItem(position).name
        view.findViewById<TextView>(R.id.textview_student_phone).text = getItem(position).phone

        return view
    }

    fun remove(student: Student) {
        students.remove(student)
    }

    fun clear() {
        students.clear()
    }

    fun addAll(students: MutableList<Student>) {
        this.students.addAll(students)
    }

}