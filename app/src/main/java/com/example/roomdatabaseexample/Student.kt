package com.example.roomdatabaseexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_student")
class Student(

    @PrimaryKey
    @ColumnInfo(name = "column_student_name")
    val studentName: String
)