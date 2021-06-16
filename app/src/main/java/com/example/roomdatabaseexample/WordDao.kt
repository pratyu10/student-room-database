package com.example.roomdatabaseexample

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM table_word ORDER BY column_word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(word: Word)

    @Query("DELETE FROM table_word")
    suspend fun deleteAllStudents()

    @Query("DELETE FROM table_word")
    suspend fun deleteOneStudent(word: Word)

}