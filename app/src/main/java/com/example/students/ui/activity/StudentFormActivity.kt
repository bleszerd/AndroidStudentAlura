package com.example.students.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.students.R
import com.example.students.data.StudentsDAO
import com.example.students.model.Student

class StudentFormActivity : AppCompatActivity() {
    private val studentDAO = StudentsDAO()
    private lateinit var student: Student

    private lateinit var editTextName: EditText
    private lateinit var editTextMail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var confirmButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_form)

        //Populate Views references
        editTextName = findViewById(R.id.edittext_name)
        editTextPhone = findViewById(R.id.edittext_phone)
        editTextMail = findViewById(R.id.edittext_mail)
        confirmButton = findViewById(R.id.button_confirm_action)

        //Get student from extra
        if (intent.hasExtra("studentInfo")) {
            //Extra exists
            val extraStudent = intent.getSerializableExtra("studentInfo")
            student = extraStudent as Student
        }

        //Set button properties
        confirmButton.setOnClickListener {
            //Update student info based on EditTexts
            student.name = editTextName.text.toString()
            student.phone = editTextPhone.text.toString()
            student.email = editTextMail.text.toString()

            //Handle edit or create method here
            handleButtonConfirmActionAndDismiss()
        }

        //Update EditTexts with default student values
        editTextName.setText(student.name)
        editTextPhone.setText(student.phone)
        editTextMail.setText(student.email)
    }

    /** Handle button confirm action (Edit or delete student)*/
    private fun handleButtonConfirmActionAndDismiss() {
        if (intent.hasExtra("studentInfo")) {
            studentDAO.update(student)
        } else {
            studentDAO.add(student)
        }

        finish()
    }
}