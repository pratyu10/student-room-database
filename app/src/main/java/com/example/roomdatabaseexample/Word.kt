package com.example.roomdatabaseexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_word")
class Word(

    @PrimaryKey
    @ColumnInfo(name = "column_word")
    val word: String
)