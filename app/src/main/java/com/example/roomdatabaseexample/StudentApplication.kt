package com.example.roomdatabaseexample

import android.app.Application

class StudentApplication : Application() {

    private val database by lazy {
        StudentDatabase.getDatabase(context = this)
    }

    val repository by lazy {
        StudentRepository(database.studentDao())
    }
}