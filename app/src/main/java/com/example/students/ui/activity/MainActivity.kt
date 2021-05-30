package com.example.students.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.students.R
import com.example.students.data.StudentsDAO
import com.example.students.model.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var studentsList: ListView
    private val studentsDAO = StudentsDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Find views references
        studentsList = findViewById(R.id.listview_students)

        //Set per item OnClickListener
        studentsList.setOnItemClickListener { parent, view, position, id ->
            val goToStudentFormIntentEdit = Intent(this, StudentFormActivity::class.java)
            val selectedStudent = studentsDAO.getByPosition(position)
            goToStudentFormIntentEdit.putExtra("studentInfo", selectedStudent)
            startActivity(goToStudentFormIntentEdit)
        }

        //Set 3 students
        studentsDAO.add(Student("Diego", "email@examplo.com", "16998877854"))
        studentsDAO.add(Student("Mario", "email@examplo.com", "16998877854"))
        studentsDAO.add(Student("Fernanda", "email@examplo.com", "16998877854"))

        //Set fab OnClickListener
        val fabAddStudent: FloatingActionButton = findViewById(R.id.fab_add_student)
        fabAddStudent.setOnClickListener {
            //Define intent
            val goToStudentFormIntentAdd = Intent(this, StudentFormActivity::class.java)
            startActivity(goToStudentFormIntentAdd)
        }
    }

    override fun onResume() {
        super.onResume()

        //Set array adapter
        val studentsListArrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, studentsDAO.all())
        studentsList.adapter = studentsListArrayAdapter
    }
}