package com.example.roomdatabaseexample

import WordRepository
import android.app.Application

class WordsApplication: Application() {

    private val database by lazy { WordDatabase.getDatabase(context = this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}