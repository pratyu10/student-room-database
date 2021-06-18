package com.example.roomdatabaseexample

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * FROM table_student ORDER BY column_student_name ASC")
    fun getAlphabetizedStudents(): Flow<List<Student>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Query("DELETE FROM table_student")
    suspend fun deleteAllStudents()

    @Delete
    suspend fun deleteOneStudent(student: Student)
}