package com.example.students.data

import android.util.Log
import com.example.students.model.Student

class StudentsDAO {
    companion object {
        private var idCreator = 0
        private val studentsList: MutableList<Student> = mutableListOf()
    }

    fun add(student: Student) {
        idCreator++
        student.setId(idCreator)

        studentsList.add(student)
    }

    fun update(student: Student) {
        for (s in studentsList) {
            if (s.getId() == student.getId()) {
                val studentIndex = studentsList.indexOf(s)
                studentsList[studentIndex] = student

                return
            }
        }
    }

    fun remove(student: Student) {

    }

    fun all(): MutableList<Student> {
        return studentsList
    }

    fun listSize(): Int {
        return studentsList.size
    }

    fun getByPosition(position: Int): Student {
        return studentsList[position]
    }

}