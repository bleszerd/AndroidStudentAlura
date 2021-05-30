package com.example.students.model

import java.io.Serializable

class Student(var name: String, var email: String, var phone: String) : Serializable {
    private var id: Int = 0

    fun getId(): Int {
        return this.id
    }

    fun setId(id: Int) {
        this.id = id
    }

    override fun toString(): String {
        return "$name"
    }

}