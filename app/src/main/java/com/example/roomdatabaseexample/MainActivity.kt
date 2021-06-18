package com.example.roomdatabaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as StudentApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.students_recycler_view)
        val editStudent: EditText = findViewById(R.id.edit_student)
        val button: Button = findViewById(R.id.button_save)

        val adapter = StudentListAdapter(listener)
        recyclerView.adapter = adapter

        studentViewModel.allStudents.observe(this, { list ->
            adapter.submitList(list)
        })

        button.setOnClickListener {
            val name = editStudent.text.toString()
            studentViewModel.insert(Student(name))
        }
        val buttonDeleteAll: Button = findViewById(R.id.delete_all)

        buttonDeleteAll.setOnClickListener {
            studentViewModel.deleteAll()
        }
    }

    val listener = StudentListAdapter.OnClickListener{
        studentViewModel.deleteSelectedStudent(it)
    }
}