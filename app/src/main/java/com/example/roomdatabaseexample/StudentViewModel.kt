package com.example.roomdatabaseexample

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class StudentViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allStudents: LiveData<List<Student>> = repository.allStudents.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }

    fun deleteSelectedStudent(student: Student){
        viewModelScope.launch {
            repository.deleteSingleStudent(student)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}