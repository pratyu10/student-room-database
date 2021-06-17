package com.example.roomdatabaseexample

import WordRepository
import WordViewModel
import WordViewModelFactory
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var wordViewModel: WordViewModel
//
//    private val wordViewModel: WordViewModel by viewModels {
//        WordViewModelFactory((application as WordsApplication).repository)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val application: Application = getApplication()
        val repository: WordRepository = (application as WordsApplication).repository
        val factory = WordViewModelFactory(repository)
        wordViewModel = ViewModelProvider(this, factory).get(WordViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.students_recycler_view)

        val adapter = WordListAdapter()
        recyclerView.adapter = adapter

        val editStudent: EditText = findViewById(R.id.edit_student)
        val button: Button = findViewById(R.id.button_save)
        button.setOnClickListener {
            val name = editStudent.text.toString()
            wordViewModel.insert(Word(name))
        }

        wordViewModel.allWords.observe(this, { list ->
            adapter.submitList(list)
        })
    }

}