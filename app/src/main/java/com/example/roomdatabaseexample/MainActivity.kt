package com.example.roomdatabaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.words_recycler_view)
        val editStudent: EditText = findViewById(R.id.edit_student)
        val button: Button = findViewById(R.id.button_save)

        val adapter = WordListAdapter()
        recyclerView.adapter = adapter

        wordViewModel.allWords.observe(this, { list ->
            adapter.submitList(list)
        })

        button.setOnClickListener {
            val name = editStudent.text.toString()
            wordViewModel.insert(Word(name))
        }
    }
}