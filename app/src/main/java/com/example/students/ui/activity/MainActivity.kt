package com.example.students.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import com.example.students.data.Constants.Companion.STUDENT_EXTRA_KEY
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.students.R
import com.example.students.adapter.StudentAdapter
import com.example.students.data.StudentsDAO
import com.example.students.model.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var studentsList: ListView
    private val studentsDAO = StudentsDAO()
    private lateinit var studentsAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getStudentListViewReference()

        setStudentsListViewAdapter()

        setOnItemClickListener()

        _TEST_addStudents()

        setFabOnClickListener()

        registerForContextMenu(studentsList)
    }

    override fun onResume() {
        super.onResume()

        updateStudentsUiData()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.acitivity_main_menu, menu)  //Attach XLM menu to context menu
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        // Handle context menu item click
        val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo?


        when (item.itemId) {
            R.id.contextMenu_item_remove_student -> {
                val selectedStudent = studentsAdapter.getItem(menuInfo?.position!!)
                removeStudent(selectedStudent!!)
            }
        }

        return super.onContextItemSelected(item)
    }

    /** Clear list and get students list from DAO */
    private fun updateStudentsUiData() {
        studentsAdapter.clear()
        studentsAdapter.addAll(studentsDAO.all())
    }

    /** Set fab onClick and intent for navigation */
    private fun setFabOnClickListener() {
        val fabAddStudent: FloatingActionButton = findViewById(R.id.fab_add_student)
        fabAddStudent.setOnClickListener {
            //Define intent
            val goToStudentFormIntentAdd = Intent(this, StudentFormActivity::class.java)
            startActivity(goToStudentFormIntentAdd)
        }
    }

    /** [TEST_ONLY] Add 3 students */
    private fun _TEST_addStudents() {
        studentsDAO.add(Student("Diego", "email@examplo.com", "16998877854"))
        studentsDAO.add(Student("Mario", "email@examplo.com", "16998877854"))
        studentsDAO.add(Student("Fernanda", "email@examplo.com", "16998877854"))
    }

    /** Remove student and update UI */
    private fun removeStudent(selectedStudent: Student) {
        studentsDAO.remove(selectedStudent)
        studentsAdapter.remove(selectedStudent)
    }

    /** Set item click to edit */
    private fun setOnItemClickListener() {
        studentsList.setOnItemClickListener { _, _, position, _ -> //parent, view, position, id
            val goToStudentFormIntentEdit = Intent(this, StudentFormActivity::class.java)
            val selectedStudent = studentsDAO.getByPosition(position)
            goToStudentFormIntentEdit.putExtra(STUDENT_EXTRA_KEY, selectedStudent)
            startActivity(goToStudentFormIntentEdit)
        }
    }

    /** Set studentsList adapter */
    private fun setStudentsListViewAdapter() {
        val studentsListAdapter = StudentAdapter(this)

        studentsAdapter = studentsListAdapter       //Store adapter on class
        studentsList.adapter = studentsAdapter      //Update view adapter
    }

    /** Get studentListView reference */
    private fun getStudentListViewReference() {
        studentsList = findViewById(R.id.listview_students)
    }
}